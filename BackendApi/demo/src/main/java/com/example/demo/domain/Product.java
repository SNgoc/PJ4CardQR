package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String name;

    private String url;

    private String imageUrlcode;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private int status;

    private String token;

    private LocalDateTime create_at;

    private LocalDateTime update_at;

    private LocalDateTime delete_at;

    public Product(String description, String name, String url, String imageUrlcode, User user, LocalDateTime create_at,int status,String token) {
        this.description = description;
        this.name = name;
        this.url = url;
        this.imageUrlcode = imageUrlcode;
        this.user = user;
        this.create_at = create_at;
        this.status = status;
        this.token = token;
    }
}
