package com.user.curd.user_crud.data.repository;

import com.user.curd.user_crud.data.entity.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity,Integer> {
    UserDetailsEntity findByEmail(String email);

}
