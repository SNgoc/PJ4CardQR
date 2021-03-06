package com.example.projectclient.Controllers.Admin;

import com.example.projectclient.Config.JSONUtils;
import com.example.projectclient.Models.Category;
import com.example.projectclient.Models.Orders;
import com.example.projectclient.Service.OrderService;
import com.example.projectclient.Service.PdfService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    PdfService pdfService;

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

    @GetMapping("Admin/Order/Confirm/{id}")
    public String Confirm(@PathVariable int id, HttpSession session,  RedirectAttributes redirectAttributes) throws IOException, InterruptedException {
        var response = orderService.confirm(id,session);
        if (response.statusCode() != 200){
            redirectAttributes.addFlashAttribute("Success",response.body());
            return "redirect:/Admin/OrderDetails-" + id;
        }
        redirectAttributes.addFlashAttribute("Success",response.body());
        return "redirect:/Admin/OrderDetails-" + id;
    }

    @GetMapping("Admin/Order/cancelOrder/{id}")
    public String Cancel(@PathVariable int id, HttpSession session,  RedirectAttributes redirectAttributes) throws IOException, InterruptedException {
        var response = orderService.cancel(id,session);
        if (response.statusCode() != 200){
            redirectAttributes.addFlashAttribute("Success",response.body());
            return "redirect:/Admin/OrderDetails-" + id;
        }
        redirectAttributes.addFlashAttribute("Success",response.body());
        return "redirect:/Admin/OrderDetails-" + id;
    }

    @GetMapping("Admin/OrderDetails-{id}")
    public String GoDetails(@PathVariable Long id, HttpSession session,Model model,RedirectAttributes redirectAttributes)throws IOException, InterruptedException{
        var response = orderService.details(id,session);
        JSONObject ob = new JSONObject(response.body());
        Orders orders = JSONUtils.convertToObject(Orders.class,ob.toString());
        model.addAttribute("orders", orders);
        //create pdf file
        pdfService.Create(orders.getProduct().getId(),orders.getFullname(), orders.getProduct().getImageUrlcode());
        model.addAttribute("pdf", "./product-pdf/"+ orders.getProduct().getId().toString()+".pdf");
        return "/Admin/Orders/OrderDetails";
    }


}
