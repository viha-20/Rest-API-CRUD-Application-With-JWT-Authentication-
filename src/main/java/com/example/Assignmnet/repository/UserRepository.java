package com.example.Assignmnet.repository;

import com.example.Assignmnet.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData,String> {

     UserData findByEmail(String email);

}