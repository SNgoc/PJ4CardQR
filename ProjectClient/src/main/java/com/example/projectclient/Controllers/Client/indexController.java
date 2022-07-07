package com.example.projectclient.Controllers.Client;

import com.example.projectclient.Config.JSONUtils;
import com.example.projectclient.Models.Category;
import com.example.projectclient.Models.CreateOrderRequest;
import com.example.projectclient.Service.CategoryService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller

public class indexController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/Client")
    public String index(HttpSession session, RedirectAttributes attributes){

        return "Client/index";
    }

    @GetMapping("/Login")
    public String login(HttpSession session, RedirectAttributes attributes){
        return "Client/Login";
    }

    @GetMapping("/changeEmail")
    public String changeEmail(HttpSession session, RedirectAttributes attributes){
        return "Client/ChangeEmail";
    }

    @GetMapping("/resetPassword")
    public String resetPassword(HttpSession session, RedirectAttributes attributes){
        return "Client/ResetPassword";
    }

    @GetMapping("/Order")
    public String getOrder(ModelMap model,HttpSession session) throws IOException, InterruptedException {
        if (session.getAttribute("token") == null){

            return "redirect:/Login";
        }

        var response = categoryService.ShowAll(session);
        JSONArray ob = new JSONArray(response.body());
        Category[] categories = JSONUtils.convertToObject(Category[].class,ob.toString());
        assert categories != null;
        model.addAttribute("categoriOrder", List.of(categories));
        model.addAttribute("CreateOrder", new CreateOrderRequest());
        return "Client/Order";
    }
    @GetMapping("/category")
    public String category(HttpSession session, ModelMap model) throws IOException, InterruptedException {
        var response = categoryService.ShowAll(session);
        JSONArray ob = new JSONArray(response.body());
        Category[] categories = JSONUtils.convertToObject(Category[].class,ob.toString());
        assert categories != null;
        model.addAttribute("categories", List.of(categories));

        return "Client/Category";
    }

    @GetMapping("/ProductDetails")
    public String productDetails(){
        return "Client/ProductDetails";
    }
}
