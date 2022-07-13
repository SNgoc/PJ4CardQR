package com.example.demo.Controllers;

import com.example.demo.Payload.Request.AddCategoryRequest;
import com.example.demo.domain.Category;
import com.example.demo.domain.LinkType;
import com.example.demo.domain.UrlProduct;
import com.example.demo.repo.LinkTypeRepository;
import com.example.demo.service.UrlProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/urlProduct")
public class UrlProductController {
    @Autowired
    UrlProductService productService;
    @Autowired
    LinkTypeRepository linkTypeRepository;

    @GetMapping("/list/{username}")
    public ResponseEntity<?> ShowAllByUsername(@PathVariable String username) {
        return ResponseEntity.ok(productService.ShowAllByUser(username));
    }

    @GetMapping("/listLinkType")
    public ResponseEntity<?> ShowAllLinkType() {
        return ResponseEntity.ok((List<LinkType>)linkTypeRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(UrlProduct url) throws IOException {
        String response = productService.addUrl(url);
        if(response != null){
            return ResponseEntity.ok("OK");
        }else{
            return ResponseEntity.badRequest().body("Fail");
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        String response = productService.deleteUrl(id);
        if(response=="OK"){
            return new ResponseEntity("Success", HttpStatus.OK);
        }else{
            return new ResponseEntity("ID Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
