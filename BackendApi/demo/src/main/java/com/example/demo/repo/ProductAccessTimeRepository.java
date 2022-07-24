package com.example.demo.repo;

import com.example.demo.domain.ProductAccessTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface ProductAccessTimeRepository extends CrudRepository<ProductAccessTime,Long> {

//    @Query(value = "select count(id) from ProductAccessTime  GROUP BY day(create_at)")
//    int countProductAccessTimeByCreate_atNow();
//
//    @Query(value = "select d from ProductAccessTime d where d.create_at between date_sub(now(),interval 1 WEEK) AND NOW()",nativeQuery = true)
//    List<ProductAccessTime> findProductAccessTimeByCreate_at();
//
//    @Query(value = "select count(id) from ProductAccessTime  GROUP BY day(create_at)")
//    int countProductAccessTimeByCreate_atMonth();
//
//    @Query(value = "select count(id) from ProductAccessTime  GROUP BY day(create_at)")
//    int countProductAccessTimeByCreate_atYear();
}
