package com.example.projectclient.Controllers.Client;

import com.example.projectclient.Config.JSONUtils;
import com.example.projectclient.Models.*;
import com.example.projectclient.Service.CategoryService;
import com.example.projectclient.Service.ReviewService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller

public class indexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/Client")
    public String index(HttpSession session, ModelMap model) throws IOException, InterruptedException {
        var response = categoryService.ShowAll(session);
        JSONArray ob = new JSONArray(response.body());
        Category[] categories = JSONUtils.convertToObject(Category[].class,ob.toString());
        model.addAttribute("categories", List.of(categories));
        return "Client/index";
    }

    @GetMapping("/Login")
    public String login(HttpSession session, RedirectAttributes attributes, Model model)
    {
        model.addAttribute("SignUpRequest", new SignUpRequest());
        model.addAttribute("changePassword",new changePasswordReset());
        return "Client/Login";
    }

    @GetMapping("/changeEmail")
    public String changeEmail(HttpSession session, Model model){

        return "Client/ChangeEmail";
    }

    @GetMapping("/resetPassword")
    public String resetPassword(HttpSession session, Model model)
    {
        model.addAttribute("changePassword",new changePasswordReset());
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

    @GetMapping("/Order-{id}")
    public String getOrderCategory(@PathVariable Long id,ModelMap model,HttpSession session) throws IOException, InterruptedException {
        if (session.getAttribute("token") == null){

            return "redirect:/Login";
        }
        var category = categoryService.details(id,session);
        model.addAttribute("CreateOrder", new CreateOrderRequest(category.getId()));
        model.addAttribute("category", category);
        return "Client/Order";
    }


    @GetMapping("/Shopping")
    public String Shopping(HttpSession session, ModelMap model) throws IOException, InterruptedException {
        var response = categoryService.ShowAll(session);
        JSONArray ob = new JSONArray(response.body());
        Category[] categories = JSONUtils.convertToObject(Category[].class,ob.toString());
        assert categories != null;
        model.addAttribute("categories", List.of(categories));
        return "/Client/Shopping";
    }

    @GetMapping("/Details-{id}")
    public String productDetails(@PathVariable Long id, HttpSession session, ModelMap model)throws IOException, InterruptedException{
        var response = categoryService.details(id,session);
        var reviewCategory = reviewService.FindReviewCategory(id,session);
        JSONArray ob = new JSONArray(reviewCategory.body());
        Review[]  review= JSONUtils.convertToObject(Review[].class,ob.toString());
        if (response == null){
            return "error/404";
        }else{
            assert review != null;
            model.addAttribute("review", List.of(review));
            model.addAttribute("category", response);
            return "Client/CategoryDetails";
        }
    }

    @GetMapping("/Reviews-{id}")
    public String Review(@PathVariable Long id,Model model,HttpSession session){
        model.addAttribute("PostReview", new ReviewRequest());
        session.setAttribute("categoryId",id);
        return "Client/Review";
    }

    @PostMapping("/postComment")
    public String postReview( RedirectAttributes attributes,Model model, @ModelAttribute("Review")ReviewRequest request,HttpSession httpSession) throws IOException, InterruptedException {
        request.setCategory_id((Long) httpSession.getAttribute("categoryId"));
        request.setUser_id((Long) httpSession.getAttribute("id"));
        String json = JSONUtils.convertToJSON(request);

        var response = reviewService.addReview(json,httpSession);
        System.out.println(response.statusCode());
        if (response.statusCode() == 500){
            JSONObject ob = new JSONObject(response.body());
            attributes.addFlashAttribute("errorMessage",ob.getString("message"));
            return "redirect:/Reviews-"+httpSession.getAttribute("categoryId");
        }

        return "redirect:/Product";
    }
}
