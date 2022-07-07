package com.example.demo.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
public class UrlProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String url;

    private int Image;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

//    @ManyToOne
//    @JoinColumn(name="type_id")
//    private LinkType linkType;
}
