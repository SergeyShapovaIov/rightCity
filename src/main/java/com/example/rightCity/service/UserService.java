package com.example.rightCity.service;

import com.example.rightCity.entity.UserEntity;
import com.example.rightCity.exception.user.CombinationMailPasswordException;
import com.example.rightCity.exception.user.OldNameMatchesNewOneException;
import com.example.rightCity.exception.user.UserNotFoundException;
import com.example.rightCity.exception.user.UserWithMailAlreadyExistException;
import com.example.rightCity.repository.UserRepo;
import org.springframework.stereotype.Service;

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


    public UserEntity updateUsernameById(String username, Long id) throws OldNameMatchesNewOneException {
        UserEntity user = userRepo
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        checkMatches(username, user);

        user.setFIO(username);

        return userRepo.save(user);
    }


    public UserEntity updatePasswordById(String password, Long id) {
        UserEntity user = userRepo
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        user.setPassword(password);

        return userRepo.save(user);
    }

    public void deleteUserById(Long id){
        userRepo
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        userRepo.deleteById(id);
    }

    public void loginByMailPassword(UserEntity user)
            throws UserNotFoundException, CombinationMailPasswordException {
        checkFoundByMail(user.getMail());

        checkPassword(user.getMail(), user.getPassword());
    }


    public UserEntity getUserByMail(String mail) throws UserNotFoundException {
        checkFoundByMail(mail);

        return userRepo.findByMail(mail);
    }


    private void checkFoundByMail(String mail) throws UserNotFoundException {
        if(Objects.equals(userRepo.findByMail(mail), null)) {
            throw new UserNotFoundException();
        }
    }


    private void checkPresent(UserEntity user) throws UserWithMailAlreadyExistException {
        if(!Objects.equals(userRepo.findByMail(user.getMail()), null)) {
            throw new UserWithMailAlreadyExistException();
        }
    }


    private void checkMatches(String username, UserEntity user) throws OldNameMatchesNewOneException {
        if(Objects.equals(user.getFIO(), username)) {
            throw new OldNameMatchesNewOneException();
        }
    }


    private void checkPassword(String mail, String password) throws CombinationMailPasswordException {
        if(!Objects.equals(userRepo.findByMail(mail).getPassword(), password)) {
            throw new CombinationMailPasswordException();
        }
    }
}
