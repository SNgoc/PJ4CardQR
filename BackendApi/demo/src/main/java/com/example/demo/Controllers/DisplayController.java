package com.example.demo.Controllers;

import com.example.demo.domain.Product;
import com.example.demo.repo.ProductRepository;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/display")
public class DisplayController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ProductRepository productRepository;
    //Display Product
    @GetMapping("/{username}")
    public ResponseEntity<?> displayProduct(@PathVariable String username) {
        var user = userRepo.findByUsername(username);
        Product product = productRepository.findProductByUser(user.get());
        if(product.getStatus() ==1){
//            int expireYear = product.getCreatedAt().getYear() + product.getYear();
//            if(new Date().getYear() >= expireYear){
//
//            }

            product.setCount(product.getCount()+1);
            productRepository.save(product);
            return ResponseEntity.ok(product);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fails");
        }
    }
}
