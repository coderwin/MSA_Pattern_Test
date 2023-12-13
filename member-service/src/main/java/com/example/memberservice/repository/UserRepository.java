package com.example.memberservice.repository;


import com.example.memberservice.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser,Long> {
}
