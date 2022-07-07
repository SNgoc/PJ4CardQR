package com.example.demo.repo;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);


    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enable = TRUE , a.locked = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

    User findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query("SELECT user FROM User user LEFT JOIN user.roles role WHERE role.id = ?1")
    List<User> findUserByRole(Integer role);


}
