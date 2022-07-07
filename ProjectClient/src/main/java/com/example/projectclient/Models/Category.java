package com.example.projectclient.Models;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    private Long id;

    @Min(value = 1, message = "Price must be greater than 0")
    private int price;

    @NotBlank(message = "name is not blank")
    private String name;

    private String url;

    private String secretSeri;

    private String frontImage;

    private String backImage;

    private Date create_at;

    private Date update_at;

    private Date delete_at;

    public Category(int price, String name) {
        this.price = price;
        this.name = name;
    }
}
