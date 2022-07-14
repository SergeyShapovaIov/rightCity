package com.example.rightCity.service;

import com.example.rightCity.entity.UserEntity;
import com.example.rightCity.exception.CombinationMailPasswordException;
import com.example.rightCity.exception.OldNameMatchesNewNameException;
import com.example.rightCity.exception.UserNotFoundException;
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


    public UserEntity updateUsernameById(String username, Long id) throws OldNameMatchesNewNameException {
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

    public void loginByMailPassword(UserEntity user) throws UserNotFoundException, CombinationMailPasswordException {
        checkFoundByMail(user.getMail());

        checkCombinationMailPassword(user.getMail(), user.getPassword());
    }



    public UserEntity getUserByMail(String mail) throws UserNotFoundException {
        checkFoundByMail(mail);

        return userRepo.findByMail(mail);
    }

    private void checkFoundByMail(String mail) throws UserNotFoundException {
        if(userRepo.findByMail(mail) == null){
            throw new UserNotFoundException("No user with this email was found");
        }
    }


    private void checkPresent(UserEntity user) throws UserWithMailAlreadyExistException {
        if(userRepo.findByMail(user.getMail()) != null){
            throw new UserWithMailAlreadyExistException("User with this email is already exist!");
        }
    }


    private void checkMatches(String username, UserEntity user) throws OldNameMatchesNewNameException {
        if(Objects.equals(user.getFIO(), username)) {
            throw new OldNameMatchesNewNameException("The old name matches the new name");
        }
    }

    private void checkCombinationMailPassword (String mail, String password) throws CombinationMailPasswordException {
        if(!Objects.equals(userRepo.findByMail(mail).getPassword(), password)){
            throw new CombinationMailPasswordException("Incorrect user/password combination");
        }
    }
}
