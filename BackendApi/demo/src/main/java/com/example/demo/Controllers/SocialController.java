package com.example.demo.Controllers;

import com.example.demo.Exception.ApiRequestException;
import com.example.demo.Payload.Request.SocialNWebRequest;
import com.example.demo.Payload.Request.UpdateProfile;
import com.example.demo.domain.SocialNweb;
import com.example.demo.domain.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.SocialNWebService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/api/social" , method = {RequestMethod.POST,RequestMethod.GET})
public class SocialController {
    @Autowired
    private SocialNWebService socialNWebService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/addSocial")
    public ResponseEntity<?> addSocial(@Valid @RequestBody SocialNWebRequest request){//need @request body to pass json data
        var user = userRepo.findById(request.getUser_id());
        SocialNweb socialNweb = new SocialNweb(request.getFacebook(),request.getTwitter(),request.getInstagram(),
                request.getTiktok(),request.getWeb1(),request.getWeb2(),
                request.getCompany1(), request.getCompany2(),user.get());
        SocialNweb social = socialNWebService.InsertOrUpdate(socialNweb);
        return ResponseEntity.ok(social);
    }

    //update social link
    @PostMapping("/updateSocial/{social_id}")
    public ResponseEntity<?> updateSocial(@PathVariable("social_id") Long social_id, @Valid @RequestBody SocialNWebRequest request){
        var social = socialNWebService.getSocialNwebById(social_id);
        social.setFacebook(request.getFacebook());
        social.setTwitter(request.getTwitter());
        social.setInstagram(request.getInstagram());
        social.setTiktok(request.getTiktok());
        social.setWeb1(request.getWeb1());
        social.setWeb2(request.getWeb2());
        social.setCompany1(request.getCompany1());
        social.setCompany2(request.getCompany2());
        socialNWebService.InsertOrUpdate(social);
        return ResponseEntity.ok(social);
    }

    //get social link
    @GetMapping("/getSocialAndProfile/{user_id}")
    public ResponseEntity<?> getSocialByUserID(@PathVariable("user_id") Long user_id){
        SocialNweb social = socialNWebService.getSocialNWeb(user_id);
//        SocialNWebRequest socialNWebRequest = new SocialNWebRequest();
//        socialNWebRequest.setSocial_id(social.getSocial_id());
//        socialNWebRequest.setFacebook(social.getFacebook());
//        socialNWebRequest.setTwitter(social.getTwitter());
//        socialNWebRequest.setInstagram(social.getInstagram());
//        socialNWebRequest.setTiktok(social.getTiktok());
//        socialNWebRequest.setWeb1(social.getWeb1());
//        socialNWebRequest.setWeb2(social.getWeb2());
//        socialNWebRequest.setCompany1(social.getCompany1());
//        socialNWebRequest.setCompany2(social.getCompany2());
//        socialNWebRequest.setUser_id(social.getUser().getId());
        return ResponseEntity.ok(social);
    }

    //change imge avatar by url
    @PostMapping("/avatarUrl/changeAvatarUrl/{id}")
    public ResponseEntity<?> changeAvatar(@PathVariable("id") Long id, @RequestBody String imgUrl) throws IOException {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            User u = user.get();
            u.setLinkImage(imgUrl);
            userRepo.save(u);
//            return ResponseEntity.ok("Update Succesfully");//trả về kiểu string sẽ gây ra lỗi Begin Expect Object onFailure bên Android
            return ResponseEntity.ok(u);//trả về kiểu obj để fix lỗi này
        } else {
            throw new ApiRequestException( "Save change fails");
        }
    }
}
