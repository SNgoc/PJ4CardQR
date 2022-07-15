package com.example.projectclient.Controllers.Client;

import com.example.projectclient.Config.JSONUtils;
import com.example.projectclient.Models.*;
import com.example.projectclient.Service.ProductService;
import com.example.projectclient.Service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @GetMapping("/Product")
    public String MyProduct(HttpSession session, ModelMap model) throws IOException, InterruptedException {
        var myProduct = productService.GetProduct(session);
        model.addAttribute("Product", myProduct);
        model.addAttribute("listUrl", productService.ShowAllUrl(session));
        model.addAttribute("listLinkType", productService.ShowAllLinkType(session));
        model.addAttribute("UrlProduct", new UrlProduct());
        return "Client/Product";
    }

    @GetMapping("/Display/{username}")
    public String DisplayProduct(@PathVariable String username, HttpSession session, ModelMap model) throws IOException, InterruptedException {
        if(userService.checkUsername(username, session)){
            var myProduct = productService.GetProduct(session);
            model.addAttribute("Product", myProduct);
            model.addAttribute("listUrl", productService.ShowAllUrl(session));
            return "Client/DisplayProduct";
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
}
