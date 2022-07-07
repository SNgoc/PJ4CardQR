package com.example.projectclient.Models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;

    private String description;

    private String name;

    private String url;

    private String imageUrlcode;


    private User user;

    private int status;

    private String token;

    private Date create_at;

    private LocalDateTime update_at;

    private LocalDateTime delete_at;




}
