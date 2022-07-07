package com.example.projectclient.Controllers.Client;


import com.example.projectclient.Service.Client.LoginClientService;
import com.example.projectclient.Service.LoginService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@SessionAttributes({"token"})
public class LoginController {
    private final LoginClientService loginService;

    public LoginController(LoginClientService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, ModelMap model, HttpSession session,
                        RedirectAttributes attributes) throws IOException, InterruptedException {
        var response = loginService.login(username,password);
        JSONObject ob = new JSONObject(response.body());
        if (response.statusCode() != 200){
            model.addAttribute("error",ob.getString("message"));
            return "Client/Login";
        }

        String roles = ob.getJSONArray("roles").toString();


        Long id = ob.getLong("id");
        String usernames = ob.getString("username");
        String linkImage = ob.getString("linkImage");
        String nameImage = ob.getString("nameImage");
        String emails = ob.getString("email");
        String tokenType = ob.getString("tokenType");
        String token = ob.getString("accessToken");

        session.setAttribute("usernamesClient",usernames);
        session.setAttribute("linkImage",linkImage);
        session.setAttribute("nameImage",nameImage);
        session.setAttribute("roles",roles);
        session.setAttribute("emails",emails);
        session.setAttribute("tokenType",tokenType);
        session.setAttribute("token",token);
        session.setAttribute("id",id);


        return "redirect:/Client";
    }
}
