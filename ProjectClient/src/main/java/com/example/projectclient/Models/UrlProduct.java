package com.example.projectclient.Models;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlProduct {
    private Long id;

    private String title;

    private String url;

    private int Image;

    private Product product;
}
