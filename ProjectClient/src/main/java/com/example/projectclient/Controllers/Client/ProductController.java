package com.example.projectclient.Controllers.Client;

import com.example.projectclient.Config.JSONUtils;
import com.example.projectclient.Models.*;
import com.example.projectclient.Service.CloudBinary.CloudBinaryService;
import com.example.projectclient.Service.ProductService;
import com.example.projectclient.Service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @Autowired
    private CloudBinaryService cloudBinaryService;
    @Value("${javadocfast.cloudinary.folder.product}")
    private String image;
    private Map<String, String> options = new HashMap<>();

    @GetMapping("/Product")
    public String MyProduct(HttpSession session, ModelMap model) throws IOException, InterruptedException {
        if(productService.GetProduct(session) != null){
            Product product = productService.GetProduct(session);
            EditProduct editProduct = new EditProduct(product.getId(), product.getDescription(), product.getName());
            model.addAttribute("EditProduct", editProduct);
            model.addAttribute("Product",  product);
            model.addAttribute("listUrl", productService.ShowAllUrl(session));
            model.addAttribute("listLinkType", productService.ShowAllLinkType(session));
            model.addAttribute("UrlProduct", new UrlProduct());
            return "CLient/Product";
        }
       return "error/404";
    }

    @GetMapping("/Display/{username}")
    public String DisplayProduct(@PathVariable String username, HttpSession session, ModelMap model) throws IOException, InterruptedException {
        if(userService.checkUsername(username, session)){
            var myProduct = productService.Display(session);
            model.addAttribute("Product", myProduct);
            model.addAttribute("listUrl", productService.ShowAllUrl(session));
            return "CLient/DisplayProduct";
        }
        return "Client/error";
    }

    @PostMapping("/Product/AddNewUrl")
    public String AddNewUrl(@ModelAttribute("UrlProduct") UrlProduct urlProduct,@RequestParam("type") Long linkTypeId, HttpSession session, ModelMap model) throws IOException, InterruptedException {
        urlProduct.setProduct(productService.GetProduct(session));
        urlProduct.setUser(productService.GetProduct(session).getUser());
        var response = productService.addUrl(urlProduct,linkTypeId, session);
        if(response.statusCode() == 200){
            return "redirect:/Product";
        }
        return "Client/error";
    }

    @PostMapping("/Product/EditUrl")
    public String AddNewUrl(@PathVariable("urlId") Long urlId,@PathVariable("name") String name,
                            @PathVariable("type") Long linkTypeId,@PathVariable("url") String url,
                            HttpSession session) throws IOException, InterruptedException {
        UrlProduct urlProduct = new UrlProduct();
        urlProduct.setId(urlId);
        urlProduct.setName(name);
        urlProduct.setUrl(url);
        urlProduct.setProduct(productService.GetProduct(session));
        urlProduct.setUser(productService.GetProduct(session).getUser());
        var response = productService.addUrl(urlProduct,linkTypeId, session);
        if(response.statusCode() == 200){
            return "redirect:/Product";
        }
        return "Client/error";
    }

    @PostMapping("/Product/Edit")
    public String Edit(@ModelAttribute("EditProduct") EditProduct editProduct, HttpSession session, ModelMap model) throws IOException, InterruptedException {
        Product product = productService.GetProduct(session);
        product.setName(editProduct.getName());
        product.setDescription(editProduct.getDescription());
        product.setUpdate_at(new Date());

        options.put("folder", image);
        if(!editProduct.getAvatar().isEmpty()){
            // Folder To Save Image
            MultipartFile frontFile = editProduct.getAvatar();
            // Update New Image
            Map frontResult = cloudBinaryService.upload(frontFile, options);
            product.setAvatar(frontResult.get("url").toString());
        }
        String json = JSONUtils.convertToJSON(product);
        var response  = productService.edit(json,session);
        if(response.statusCode() == 200){
            return "redirect:/Product";
        }
        return "Client/error";
    }
}
