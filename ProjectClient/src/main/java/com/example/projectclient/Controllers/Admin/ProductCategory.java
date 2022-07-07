package com.example.projectclient.Controllers.Admin;

import com.example.projectclient.Config.JSONUtils;
import com.example.projectclient.Models.Category;
import com.example.projectclient.Models.Product;
import com.example.projectclient.Service.ProductService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@MultipartConfig
@Validated
public class ProductCategory {
    @Autowired
    ProductService productService;

    public ProductCategory(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("Admin/Product")
    public String ShowAll(HttpSession session, Model model) throws IOException, InterruptedException {
        var response = productService.ShowAll(session);
        JSONArray ob = new JSONArray(response.body());
        Product[] products = JSONUtils.convertToObject(Product[].class,ob.toString());
        assert products != null;
        model.addAttribute("products", List.of(products));
        return "Admin/Product/index";
    }

}
