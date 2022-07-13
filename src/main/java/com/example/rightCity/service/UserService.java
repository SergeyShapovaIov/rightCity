package com.example.rightCity.service;

import com.example.rightCity.entity.UserEntity;
import com.example.rightCity.exception.OldNameMatchesNewName;
import com.example.rightCity.exception.UserWithMailAlreadyExistException;
import com.example.rightCity.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserEntity registration (UserEntity user) throws UserWithMailAlreadyExistException {
        checkPresent(user);

        return userRepo.save(user);
    }


    public UserEntity updateUsernameById(String username, Long id) throws OldNameMatchesNewName {
        UserEntity user = userRepo
                .findById(id)
                .orElseThrow(NoSuchElementException::new);

        checkMatches(username, user);

        user.setFIO(username);

        return userRepo.save(user);
    }


    public UserEntity updatePasswordByID (String password, Long id) {
        UserEntity user = userRepo
                .findById(id)
                .orElseThrow(NoSuchElementException::new);

        user.setPassword(password);

        return userRepo.save(user);
    }

    public void deleteUserByID(Long id){
        userRepo
                .findById(id)
                .orElseThrow(NoSuchElementException::new);

        userRepo.deleteById(id);
    }



    private void checkPresent(UserEntity user) throws UserWithMailAlreadyExistException {
        if(userRepo.findByMail(user.getMail()) != null){
            throw new UserWithMailAlreadyExistException("User with this email is already exist!");
        }
    }


    private void checkMatches(String username, UserEntity user) throws OldNameMatchesNewName {
        if(Objects.equals(user.getFIO(), username)) {
            throw new OldNameMatchesNewName("The old name matches the new name");
        }
    }
}
