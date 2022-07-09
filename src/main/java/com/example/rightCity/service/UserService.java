package com.example.rightCity.service;

import com.example.rightCity.entity.UserEntity;
import com.example.rightCity.exception.UserWithMailAlreadyExistException;
import com.example.rightCity.repository.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserEntity registration (UserEntity user) throws UserWithMailAlreadyExistException {
        if(userRepo.findByMail(user.getMail()) != null){
            throw new UserWithMailAlreadyExistException("User with this email is already registered");
        }
        return userRepo.save(user);
    }
}
