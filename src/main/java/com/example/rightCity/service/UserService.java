package com.example.rightCity.service;

import com.example.rightCity.entity.UserEntity;
import com.example.rightCity.exception.user.CombinationMailPasswordException;
import com.example.rightCity.exception.user.OldNameMatchesNewOneException;
import com.example.rightCity.exception.user.UserNotFoundException;
import com.example.rightCity.exception.user.UserWithMailAlreadyExistException;
import com.example.rightCity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity registration (UserEntity user) throws UserWithMailAlreadyExistException {
        checkPresent(user);

        return userRepository.save(user);
    }


    public UserEntity updateUsernameById(String username, Long id) throws OldNameMatchesNewOneException {
        final AtomicReference<UserEntity> saved = new AtomicReference<>();

        userRepository
                .findById(id)
                        .ifPresentOrElse(user -> {
                            checkMatches(username, user);
                            user.setFIO(username);
                            saved.set(userRepository.save(user));
                        },
                                UserNotFoundException::new
                        );

        return saved.get();
    }


    public UserEntity updatePasswordById(String password, Long id) {
        final AtomicReference<UserEntity> saved = new AtomicReference<>();

        userRepository
                .findById(id)
                .ifPresentOrElse(user -> {
                    user.setPassword(password);
                    saved.set(userRepository.save(user));
                },
                        UserNotFoundException::new
                );

        return saved.get();
    }

    public void deleteUserById(Long id){
        userRepository.findById(id)
                .ifPresentOrElse(
                        user -> userRepository.deleteById(user.getID()),
                        UserNotFoundException::new
                );
    }

    public void loginByMailPassword(UserEntity user)
            throws UserNotFoundException, CombinationMailPasswordException {

        checkFoundByMail(user.getMail());

        checkPassword(user.getMail(), user.getPassword());
    }


    public UserEntity getUserByMail(String mail) throws UserNotFoundException {
        checkFoundByMail(mail);

        return userRepository.findByMail(mail);
    }


    private void checkFoundByMail(String mail) throws UserNotFoundException {
        if(Objects.equals(userRepository.findByMail(mail), null)) {
            throw new UserNotFoundException();
        }
    }


    private void checkPresent(UserEntity user) throws UserWithMailAlreadyExistException {
        if(!Objects.equals(userRepository.findByMail(user.getMail()), null)) {
            throw new UserWithMailAlreadyExistException();
        }
    }


    private void checkMatches(String username, UserEntity user) throws OldNameMatchesNewOneException {
        if(Objects.equals(user.getFIO(), username)) {
            throw new OldNameMatchesNewOneException();
        }
    }


    private void checkPassword(String mail, String password) throws CombinationMailPasswordException {
        if(!Objects.equals(userRepository.findByMail(mail).getPassword(), password)) {
            throw new CombinationMailPasswordException();
        }
    }
}
