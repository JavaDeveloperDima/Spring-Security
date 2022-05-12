package com.example.springsecuritytest.Repositories;

import com.example.springsecuritytest.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String name);
}
