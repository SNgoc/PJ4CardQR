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

    @Size(min = 6,max = 100,message = "Website is min 6 character and max 100 character")
    private String facebook;

    @Size(min = 6,max = 100,message = "Website is min 6 character and max 100 character")
    private String twitter;

    @Size(min = 6,max = 100,message = "Website is min 6 character and max 100 character")
    private String instagram;

    @Size(min = 6,max = 100,message = "Website is min 6 character and max 100 character")
    private String tiktok;

    @Size(min = 6,max = 100,message = "Website is min 6 character and max 100 character")
    private String web1;

    @Size(min = 6,max = 100,message = "Website is min 6 character and max 100 character")
    private String web2;

    @Size(min = 3,max = 100,message = "Company is min 3 character and max 100 character")
    private String company1;

    @Size(min = 3,max = 100,message = "Company is min 3 character and max 100 character")
    private String company2;

    private Long user_id;
}
