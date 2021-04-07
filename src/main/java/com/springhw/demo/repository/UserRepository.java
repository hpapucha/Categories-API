package com.springhw.demo.repository;


import com.springhw.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAddress(String userEmailAddress);

    User findByEmailAddress(String userEmailAddress);
}
