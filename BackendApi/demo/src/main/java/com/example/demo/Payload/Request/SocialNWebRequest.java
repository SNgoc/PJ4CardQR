package com.example.demo.Payload.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SocialNWebRequest {

    private Long social_id;

    private String facebook;

    private String twitter;

    private String instagram;

    private String tiktok;

    private String web1;

    private String web2;

    private String company1;

    private String company2;

    private Long user_id;
}
