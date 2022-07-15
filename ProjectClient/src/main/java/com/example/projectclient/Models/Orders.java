package com.example.projectclient.Models;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    private Long id;

    private int price;


    private Category category;


    private User user;

    private String address;

    private String phone;

    private String fullname;

    private String email;

    private Order_Process order_process;


    private Product product;
}
