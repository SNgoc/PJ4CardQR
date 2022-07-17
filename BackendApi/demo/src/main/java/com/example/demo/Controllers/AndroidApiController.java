package com.example.demo.Controllers;

import com.example.demo.Payload.Request.SocialNWebRequest;
import com.example.demo.Payload.Request.UrlAndroidRequest;
import com.example.demo.domain.LinkType;
import com.example.demo.domain.SocialNweb;
import com.example.demo.domain.UrlProduct;
import com.example.demo.repo.LinkTypeRepository;
import com.example.demo.repo.ProductRepository;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UrlProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/api/android")
public class AndroidApiController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LinkTypeRepository linkTypeRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UrlProductService urlProductService;

    //show all link type for dropdown list
    @GetMapping("/listLinkType")
    public ResponseEntity<?> ShowAllLinkType() {
        return ResponseEntity.ok((List<LinkType>)linkTypeRepository.findAll());
    }

    //add/update url for app
    @PostMapping("/addUrl")
    public ResponseEntity<?> addUrl(@Valid @RequestBody UrlAndroidRequest request){//need @request body to pass json data
        var linktype_id = linkTypeRepository.findById(request.getType_id());
        var user = userRepo.findById(request.getUser_id());
        var product_id = productRepository.findProductByUser(user.get());
        UrlProduct urlProduct = new UrlProduct();
        urlProduct.setName(request.getName());
        urlProduct.setUrl(request.getUrl());
        urlProduct.setLinkType(linktype_id.get());
        urlProduct.setProduct(product_id);
        urlProduct.setUser(user.get());
        urlProductService.addUrl(urlProduct);
        return ResponseEntity.ok(urlProduct);
    }

    //get count product was bought
    @GetMapping("/getCountProduct/")
    public int countSum(){ return productService.countProductByID(); }

    //api for revenue web and android app
    //get order list by username
    @GetMapping("/listByUsername/{username}")
    public ResponseEntity<?> getOrdersByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(orderService.getOrdersByUsername(username));
    }
    //doanh thu
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
