package fpt.aptech.projectcard.domain;

import java.util.Date;
import java.util.List;

public class Category {
    private Long id;
    private int price;
    private String name;
    private Date create_at;
    private Date update_at;
    private Date delete_at;
    private List<Product> products;

    private List<Orders> orders;

    public Category( int price, String name, Date create_at, Date update_at, Date delete_at) {

        this.price = price;
        this.name = name;
        this.create_at = create_at;
        this.update_at = update_at;
        this.delete_at = delete_at;
    }

    public Category() {
    }
}
