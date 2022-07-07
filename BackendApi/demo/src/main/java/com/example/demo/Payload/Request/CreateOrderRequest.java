package com.example.demo.Payload.Request;

import com.example.demo.domain.Category;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    private Long category_id;

    private String username;

    private String fullname;

    private String email;

    private String phone;

    private String address;

}
