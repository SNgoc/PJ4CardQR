package com.example.demo.Controllers;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductAccessTime;
import com.example.demo.repo.ProductAccessTimeRepository;
import com.example.demo.repo.ProductRepository;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("/api/display")
public class DisplayController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductAccessTimeRepository productAccessTimeRepository;
    //Display Product
    @GetMapping("/{username}")
    public ResponseEntity<?> displayProduct(@PathVariable String username) {
        var user = userRepo.findByUsername(username);
        Product product = productRepository.findProductByUser(user.get());
        if(product.getStatus() ==1){
            //check Duration
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate date = LocalDate.now();
            String strDate = date.format(formatter);
            LocalDate parsedDate = LocalDate.parse(strDate, formatter);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate2 = dateFormat.format(product.getCreatedAt());
            LocalDate parsedDate2 = LocalDate.parse(strDate2, formatter);
            LocalDate expired = parsedDate2.plusYears(product.getYear());

            if(expired.isBefore(parsedDate)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fails");
            }

            //count visit
            product.setCount(product.getCount()+1);
           var p = productRepository.save(product);
            ProductAccessTime productAccessTime = new ProductAccessTime();
            productAccessTime.setProduct(product);
            productAccessTime.setCreate_at(new Date());
            productAccessTimeRepository.save(productAccessTime);
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fails");

    }


}
