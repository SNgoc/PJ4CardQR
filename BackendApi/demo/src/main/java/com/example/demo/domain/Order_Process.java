package com.example.demo.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order_Process {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Order_Process(String name) {
        this.name = name;
    }
}
