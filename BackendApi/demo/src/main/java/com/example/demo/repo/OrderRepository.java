package com.example.demo.repo;


import com.example.demo.Payload.Request.Chart;
import com.example.demo.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@EnableJpaRepositories
@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {


    @Query("SELECT   month(createdAt), sum(price) from  Orders   group by MONTH(createdAt)  ")
    List<List<Integer>> getPriceByOrder();


    @Query("SELECT   month(createdAt), sum(price) from  Orders where category.id=?1 group by MONTH(createdAt)  ")
    List<List<Integer>> getPriceByOrderCategory(Long id);
}
