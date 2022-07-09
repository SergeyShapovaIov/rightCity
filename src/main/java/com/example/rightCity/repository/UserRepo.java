package com.example.rightCity.repository;

import com.example.rightCity.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByMail(String mail);
}
