package fpt.aptech.projectcard.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Product {

    private Long id;

    private String description;

    private String name;

    private String url;

    private String imageUrlcode;

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
