package com.example.demo.repo;


import com.example.demo.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

    //tính doanh thu(SNgoc)
    @Query(value = "select price from Orders where order_process.id = 3")
    List<Double> revenueOrder();

    //count order status(SNgoc)
    //waiting
    @Query(value = "select count(id) from Orders where order_process.id = 1")
    int orderWaiting();
    //delivery
    @Query(value = "select count(id) from Orders where order_process.id = 2")
    int orderDelivery();
    //success
    @Query(value = "select count(id) from Orders where order_process.id = 3")
    int orderSuccess();
    //cancel
    @Query(value = "select count(id) from Orders where order_process.id = 4")
    int orderCancel();
}
