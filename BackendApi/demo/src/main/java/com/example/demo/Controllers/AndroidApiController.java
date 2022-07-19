package com.example.demo.Controllers;

import com.example.demo.Payload.Request.UrlAndroidRequest;
import com.example.demo.domain.LinkType;
import com.example.demo.domain.Product;
import com.example.demo.domain.UrlProduct;
import com.example.demo.repo.LinkTypeRepository;
import com.example.demo.repo.ProductRepository;
import com.example.demo.repo.UrlProductRepository;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UrlProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//NOTE: THIS API CONTROLLER WAS PRIORITY RE-WRITTEN FOR ANDROID, NOT FOR WEB.
// PLEASE DON'T USE FOR WEB OR EDIT THEM TO AVOID EXCEPTION ERROR FOR BOTH WEB OR APP
// UPDATED: EXCEPT REVENUE API CAN USE FOR BOTH WEB AND APP


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
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UrlProductService urlProductService;
    @Autowired
    private UrlProductRepository urlRepo;

    // URL PRODUCT API FOR ANDROID
    //show all url api for URL product
    @GetMapping("/list/{username}")
    public ResponseEntity<?> ShowAllByUsername(@PathVariable String username) {
        return ResponseEntity.ok(urlProductService.ShowAllByUser(username));
    }
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

    //update url
    @PostMapping("/updateUrl/{url_id}")
    public ResponseEntity<?> updateUrl(@PathVariable("url_id") Long url_id, @Valid @RequestBody UrlAndroidRequest request){//need @request body to pass json data
        var urlProduct = urlRepo.findById(url_id).get();

        var linktype_id = linkTypeRepository.findById(request.getType_id());
        urlProduct.setName(request.getName());
        urlProduct.setUrl(request.getUrl());
        urlProduct.setLinkType(linktype_id.get());
        urlProductService.addUrl(urlProduct);//update
        return ResponseEntity.ok(urlProduct);
    }

    //ORDER API FOR ANDROID
    //get order list by username
    @GetMapping("/listByUsername/{username}")
    public ResponseEntity<?> getOrdersByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(orderService.getOrdersByUsername(username));
    }

    //PRODUCT API FOR ANDROID
    //get product by username for android
    @GetMapping("/getProduct/{username}")
    public ResponseEntity<?> getProduct(@PathVariable String username) {
        var user = userRepo.findByUsername(username);
        Product product = productRepository.findProductByUser(user.get());
        return ResponseEntity.ok(product);
    }


    ///////////////////////////////////REVENUE FOR WEB API/////////////////////////////////////////
    //get count product was bought
    @GetMapping("/getCountProduct/")
    public int countSum(){ return productService.countProductByID(); }

    //all revenue
    @GetMapping("/getRevenue")
    public double getRevenueOrder(){
        return orderService.getRevenueOrder();
    }

    //count order status
    @GetMapping("/countOrderStatus")
    public List<Integer> countOrderStatus(){
        return orderService.getSumOrderStatus();
    }

    //count users
    @GetMapping("/getCountUsers")
    public int getCountUsers(){
        return userService.countAllUsers();
    }

    @GetMapping("/getCountUsersActive")
    public int getCountUsersActive(){
        return userService.countUsersActive();
    }

    @GetMapping("/getCountUsersLocked")
    public int getCountUsersLocked(){
        return userService.countUsersLocked();
    }
    /////////////////////SN//////////////////////

    //////update
    //update social link
//    @PostMapping("/updateSocial/{social_id}")
//    public ResponseEntity<?> updateSocial(@PathVariable("social_id") Long social_id, @Valid @RequestBody SocialNWebRequest request){
//        var social = socialNWebService.getSocialNwebById(social_id);
//        social.setFacebook(request.getFacebook());
//        social.setTwitter(request.getTwitter());
//        social.setInstagram(request.getInstagram());
//        social.setTiktok(request.getTiktok());
//        social.setWeb1(request.getWeb1());
//        social.setWeb2(request.getWeb2());
//        social.setCompany1(request.getCompany1());
//        social.setCompany2(request.getCompany2());
//        socialNWebService.InsertOrUpdate(social);
//        return ResponseEntity.ok(social);
//    }
}
