package com.example.demo.Controllers;

import com.example.demo.Payload.Request.AddCategoryRequest;
import com.example.demo.domain.Category;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.CloudBinary.CloudBinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryRepository cateRepo;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(cateRepo.findAllCate());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getDetails(@PathVariable Long id) {
        if(cateRepo.existsById(id)){
            return ResponseEntity.ok(cateRepo.findById(id).get());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category not found");
        }

    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory( AddCategoryRequest category) throws IOException {
        String response = categoryService.addNew(category);
        if(response != null){
            return ResponseEntity.ok("Success");
        }else{
            return ResponseEntity.badRequest().body("Fail");
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editCategory(@RequestBody Category category) {
        if (cateRepo.existsById(category.getId())) {
            Category categoryUpdate = cateRepo.findById(category.getId()).get();
            categoryUpdate.setName(category.getName());
            categoryUpdate.setPrice(category.getPrice());
            categoryUpdate.setUpdate_at(new Date());
            cateRepo.save(categoryUpdate);
            return ResponseEntity.ok(categoryUpdate);
        } else {
            return ResponseEntity.badRequest().body("Category is not exist...");
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id) {
        Category category = cateRepo.findById(id).get();
        if (category.getDelete_at() == null) {
            category.setDelete_at(new Date());
        } else {
            category.setDelete_at(null);
        }
        cateRepo.save(category);
        return new ResponseEntity("Success", HttpStatus.OK);
    }
}
