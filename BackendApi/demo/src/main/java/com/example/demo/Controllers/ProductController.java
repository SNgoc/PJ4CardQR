package com.example.demo.Controllers;

import com.example.demo.repo.ProductRepository;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ProductRepository productRepository;
    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(productService.ShowAll());
    }

    @GetMapping("/getProduct/{user_id}")
    public ResponseEntity<?> getProduct(@PathVariable Long user_id) {
        var user = userRepo.findById(user_id);
        return ResponseEntity.ok(productRepository.findProductByUser(user.get()));
    }



}