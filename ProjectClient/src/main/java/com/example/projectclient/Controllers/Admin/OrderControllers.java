package com.example.projectclient.Controllers.Admin;

import com.example.projectclient.Config.JSONUtils;
import com.example.projectclient.Models.Category;
import com.example.projectclient.Models.Orders;
import com.example.projectclient.Service.OrderService;
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
public class OrderControllers {
    @Autowired
    OrderService orderService;

    public OrderControllers(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("Admin/Order")
    public String ShowAll(HttpSession session, Model model) throws IOException, InterruptedException {
        var response = orderService.ShowAll(session);
        JSONArray ob = new JSONArray(response.body());
        Orders[] orders = JSONUtils.convertToObject(Orders[].class,ob.toString());
        assert orders != null;
        model.addAttribute("orders", List.of(orders));
        return "Admin/Orders/index";

    }

}
