package com.example.demo.service;

import com.example.demo.domain.SocialNweb;
import com.example.demo.repo.SocialNWebRepository;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialNWebService {
    @Autowired
    private SocialNWebRepository socialNWebRepository;

    @Autowired
    private UserRepo userRepo;

    //insert or update
    public SocialNweb InsertOrUpdate(SocialNweb socialNweb) {return socialNWebRepository.save(socialNweb); }

    //search social by id
    public SocialNweb getSocialNwebById(Long social_id) {return socialNWebRepository.findById(social_id).get(); }

    //get social info by username
    public SocialNweb getSocialNWeb(Long user_id) {
        var user = userRepo.findById(user_id);
        return socialNWebRepository.findSocialNwebByUser(user.get());
    }
}
