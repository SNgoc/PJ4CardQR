package com.example.projectclient.Controllers.Admin;

import com.example.projectclient.Config.JSONUtils;
import com.example.projectclient.Models.SignUpRequest;
import com.example.projectclient.Models.User;
import com.example.projectclient.Models.changePasswordReset;
import com.example.projectclient.Service.UserService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Admin")
public class IndexControllers {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(HttpSession session, RedirectAttributes attributes){
        String role = (String) session.getAttribute("roles");
        if (session.getAttribute("token") == null  ){

            return "redirect:/Admin/login";
        }
        if (role.contains("[\"ROLE_USER\"]")){
            return "redirect:/Admin/login";
        }

        return "Admin/index";
    }

    @GetMapping("/settings")
    public String setting(ModelMap model,HttpSession session, RedirectAttributes attributes){

        String role = (String) session.getAttribute("roles");
        if (session.getAttribute("token") == null  ){

            return "redirect:/Admin/login";
        }
        if (role.contains("[\"ROLE_USER\"]")){
            return "redirect:/Admin/login";
        }

        model.addAttribute("changeEmailResetPassword",new changePasswordReset());
        return "Admin/settings";
    }
    @GetMapping("/resetPassword")
    public String resetPassword(HttpSession session,ModelMap modelMap){
        String role = (String) session.getAttribute("roles");
        if (session.getAttribute("token") == null ||role.contains("[\"ROLE_USER\"]") ){

            return "redirect:/Admin/login";
        }
        if (session.getAttribute("tokenSendMailChangePassword") ==null){
            return "redirect:/Admin/";
        }
        modelMap.addAttribute("changePasswordResetForm",new changePasswordReset());
        return "Admin/Users/ResetPassword";
    }

    @GetMapping(value = "/Users")
    public String  IndexUser(@RequestParam(value = "roles",defaultValue = "1",required = false) Integer roles, HttpSession session, ModelMap model, RedirectAttributes redirectAttributes) throws IOException, InterruptedException {
            var response = userService.FindAllUserAdmin(roles,session);
        System.out.println(response);
            if (response.statusCode() != 200){
                return "Admin/403";
            }
        JSONArray ob = new JSONArray(response.body());

        User[] user = JSONUtils.convertToObject(User[].class,ob.toString());


        assert user != null;
        model.addAttribute("listUser",List.of(user));

        return "Admin/Users/index";
    }




}
