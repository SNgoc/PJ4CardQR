package fpt.aptech.projectcard.domain;

import java.time.LocalDateTime;

public class Review {

    private Long id;

    private String fullname;

    private Product product;

    private User user;

    private int star;

    private String review;

    private LocalDateTime create_at;

    private LocalDateTime delete_at;

    private LocalDateTime update_at;


}
