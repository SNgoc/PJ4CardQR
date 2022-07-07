package com.example.projectclient.Controllers.Admin;

import com.example.projectclient.Config.JSONUtils;
import com.example.projectclient.Models.*;
import com.example.projectclient.Service.CategoryService;
import com.example.projectclient.Service.FileUploadUtil;
import com.example.projectclient.Service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@MultipartConfig
@Validated
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("Admin/Category")
    public String ShowAll(HttpSession session, Model model) throws IOException, InterruptedException {
        var response = categoryService.ShowAll(session);
        JSONArray ob = new JSONArray(response.body());
        Category[] categories = JSONUtils.convertToObject(Category[].class,ob.toString());
        assert categories != null;
        model.addAttribute("categories", List.of(categories));
        return "Admin/Category/index";
    }


    @GetMapping("Admin/Category/delete/{id}")
    public String deleteUser(@PathVariable int id, HttpSession session , RedirectAttributes redirectAttributes) throws IOException, InterruptedException {
        var response = categoryService.delete(id,session);
        if (response.statusCode() != 200){
            redirectAttributes.addFlashAttribute("Success",response.body());
            return "redirect:/Admin/Category";
        }
        redirectAttributes.addFlashAttribute("Success",response.body());
        return "redirect:/Admin/Category";
    }

    @GetMapping(value = "Category/Add")
    public String  addUser(HttpSession session, ModelMap model, RedirectAttributes redirectAttributes) throws IOException, InterruptedException {
        model.addAttribute("Category", new Category());
        return "Admin/Category/addNew";
    }

    @PostMapping(value = "/Category/AddNew")
    public String  addNewUser(@RequestParam("name") String name, @RequestParam("price") int price, @RequestParam("frontImage") MultipartFile front,
                              @RequestParam("backImage") MultipartFile back, HttpSession session,
                              RedirectAttributes redirectAttributes) throws IOException, InterruptedException, ParseException {

        String uploadDir = "user-photos";
        String frontName = StringUtils.cleanPath(front.getOriginalFilename());
        FileUploadUtil.saveFile(uploadDir, frontName, front);
        File frontFile = new File(front.getOriginalFilename());

        String backName = StringUtils.cleanPath(back.getOriginalFilename());
        FileUploadUtil.saveFile(uploadDir, backName, back);
        File backFile = new File(back.getOriginalFilename());

        var response = categoryService.add(new Category(price,name), frontFile, backFile, session);

//        if (respone.statusCode() != 200){
//            JSONObject ob = new JSONObject(respone.body());
//            Category cateParse = JSONUtils.convertToObject(Category.class,ob.toString());
//            redirectAttributes.addFlashAttribute("errorName",cateParse.getName());
//            redirectAttributes.addFlashAttribute("errorPrice",cateParse.getPrice());
//            return "redirect:/Users/Add";
//        }
        redirectAttributes.addFlashAttribute("Success","Add new Categoru successfully");
        return "redirect:/Admin/Category";
    }
}
