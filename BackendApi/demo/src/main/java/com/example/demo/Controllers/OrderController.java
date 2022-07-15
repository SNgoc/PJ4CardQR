package com.example.demo.Controllers;

import com.example.demo.Payload.Request.AddCategoryRequest;
import com.example.demo.Payload.Request.CreateOrderRequest;
import com.example.demo.domain.Orders;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepo userRepo;


    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(orderService.showAll());
    }


    @GetMapping("/details/{id}")
    public ResponseEntity<?> getDetails(@PathVariable Long id) {
        Orders order = orderService.details(id);
        if(order == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order not found");
        }
        else{
            return ResponseEntity.ok(order);
        }
    }

    @PostMapping(value="/add" , consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody CreateOrderRequest createOrderRequest) throws IOException, WriterException {
        createOrderRequest.setEmail( (userRepo.findByUsername(createOrderRequest.getUsername())).get().getEmail() );
        String response = orderService.create(createOrderRequest);
            return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/confirmProduct")
    public RedirectView confirm(@RequestParam("token") String token)  {
        return orderService.confirmToken(token);
    }

    @GetMapping("/nextProcess/{id}")
    public ResponseEntity<?> nextProcess(@PathVariable Long id) {
        Boolean next = orderService.nextProcess(id);
        if(next == false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fails");
        }
        else{
            return ResponseEntity.ok("success");
        }
    }

    @GetMapping("cancelOrder/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        Boolean cancelOrder = orderService.cancelOrder(id);
        if(cancelOrder == false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fails");
        }
        else{
            return ResponseEntity.ok("success");
        }
    }

    //doanh thu(SNgoc)
    @GetMapping("/getRevenue")
    public double getRevenueOrder(){
        return orderService.getRevenueOrder();
    }

    //count order status
    @GetMapping("/countOrderStatus")
    public List<Integer> countOrderStatus(){
        return orderService.getSumOrderStatus();
    }

}
