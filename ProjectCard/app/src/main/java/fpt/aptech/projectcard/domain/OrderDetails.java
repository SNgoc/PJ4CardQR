package fpt.aptech.projectcard.domain;

import java.time.LocalDateTime;

public class OrderDetails {

    private Long id;

    private User user;

    private Product product;

    private int quantity;

    private int price;

    private LocalDateTime create_at;
}
