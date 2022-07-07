package com.example.demo.service;

import com.example.demo.Exception.ApiRequestException;
import com.example.demo.Payload.Request.AddCategoryRequest;
import com.example.demo.Payload.Request.CategoryRequest;
import com.example.demo.domain.Category;
import com.example.demo.domain.User;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.service.CloudBinary.CloudBinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository cateRepo;

    @Value("${javadocfast.cloudinary.folder.category}")
    private String image;

    private Map<String, String> options = new HashMap<>();

    @Autowired
    private CloudBinaryService cloudinaryService;

    public String addNew(AddCategoryRequest request)throws IOException{
        Category cate = new Category(Integer.parseInt(request.getPrice()), request.getName(),new Date(), null,null);
        MultipartFile frontMulti = request.getFront();
        MultipartFile backMulti = request.getBack();

        if (frontMulti != null) {
            BufferedImage bufferedImage = ImageIO.read(frontMulti.getInputStream());
            if (bufferedImage == null) {
                throw new   ApiRequestException("Error: Invalid image");
            }
            options.put("folder", image);
            Map front = cloudinaryService.upload(frontMulti, options);

            cate.setFrontImage(front.get("url").toString());
        }

        if (backMulti != null) {
            BufferedImage bufferedImage = ImageIO.read(backMulti.getInputStream());
            if (bufferedImage == null) {
                throw new   ApiRequestException("Error: Invalid image");
            }
            options.put("folder", image);
            Map back = cloudinaryService.upload(backMulti, options);
            cate.setBackImage(back.get("url").toString());
        }
        cateRepo.save(cate);
        return "OK";
    }


}
