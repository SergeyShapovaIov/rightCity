package com.example.rightCity.service;

import com.example.rightCity.entity.UserEntity;
import com.example.rightCity.exception.OldNameMatchesNewName;
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

    public UserEntity updateUsernameByID (String username, Long id) throws OldNameMatchesNewName {
        UserEntity user = userRepo.findById(id).get();
        if(user.getFIO() == username){
            throw new OldNameMatchesNewName("The old name matches the new name");
        }
        user.setFIO(username);
        return userRepo.save(user);
    }

    public UserEntity updatePasswordByID (String password, Long id) {
        UserEntity user = userRepo.findById(id).get();
        user.setPassword(password);
        return userRepo.save(user);
    }

}
