package fpt.aptech.projectcard.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Product {

    private Long id;

    private String description;

    private LocalDateTime create_at;

    private LocalDateTime update_at;

    private LocalDateTime delete_at;

    private String link_image_back;

    private String link_image_font;

    private String name;

    private String name_image_back;

    private String name_image_font;

    private Category category;

    private List<Review> reviews;

    private List<UrlProduct> UrlProducts;

    private List<OrderDetails> orderDetails;
}
