package com.example.projectclient.Controllers.Admin;

import com.example.projectclient.Config.JSONUtils;
import com.example.projectclient.Models.CreateCategory;
import com.example.projectclient.Models.ForbiddenReview;
import com.example.projectclient.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/Admin")
public class ReviewController {

    @Autowired
    ReviewService reviewService;
    @PostMapping("/reviews/add")
    public String showAll(HttpSession session, @ModelAttribute("forbiddenword") ForbiddenReview forbiddenReview) throws IOException, InterruptedException {
        String json = JSONUtils.convertToJSON(forbiddenReview);
        var response  = reviewService.addWord(json,session);
        return "redirect:/Admin/reviews";
    }

    @GetMapping("/reviews/delete/{id}")
    public String showAll(@PathVariable Long id ,HttpSession session, @ModelAttribute("forbiddenword") ForbiddenReview forbiddenReview) throws IOException, InterruptedException {
        var response  = reviewService.deleteWord(id,session);
         if (response.statusCode() != 200){
             return "redirect:/Admin/reviews";
         }
        return "redirect:/Admin/reviews";
    }
}
