package com.example.demo.repo;

import com.example.demo.domain.SocialNweb;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialNWebRepository extends JpaRepository<SocialNweb,Long> {
    @Query(value = "SELECT s FROM SocialNweb s WHERE s.user =:user")
    SocialNweb findSocialNwebByUser(User user);
}
