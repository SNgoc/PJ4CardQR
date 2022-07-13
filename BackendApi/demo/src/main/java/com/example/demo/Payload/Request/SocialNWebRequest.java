package com.example.demo.Payload.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@Data
public class SocialNWebRequest {

    private Long social_id;

    @Size(max = 100,message = "Website is max 100 character")
    private String facebook;

    @Size(max = 100,message = "Website is max 100 character")
    private String twitter;

    @Size(max = 100,message = "Website is max 100 character")
    private String instagram;

    @Size(max = 100,message = "Website is max 100 character")
    private String tiktok;

    @Size(max = 100,message = "Website is max 100 character")
    private String web1;

    @Size(max = 100,message = "Website is max 100 character")
    private String web2;

    @Size(max = 100,message = "Company is max 100 character")
    private String company1;

    @Size(max = 100,message = "Company is max 100 character")
    private String company2;

    private Long user_id;
}
