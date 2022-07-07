package com.example.demo.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class LinkType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String placeholder;

    private String dataUrl;

    private String title;

    private String linkImage;

//    @OneToMany(mappedBy = "linkType")
//    private List<UrlProduct> UrlProducts;
}
