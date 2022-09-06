package com.example.rightCity.service;

import com.example.rightCity.entity.UserEntity;
import com.example.rightCity.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;
    private UserEntity user;
    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService(repository);

        final String randString = RandomString.make();

        user = new UserEntity();
        user.setID(new Random().nextLong());
        user.setFIO(randString.concat("FIO"));
        user.setPassword(randString.concat("PASSWORD"));
        user.setMail(randString.concat("@gmail.com"));
    }


    @Test
    void canRegistration() {
        // when
        service.registration(user);

        //then
        verify(repository.save(user));
    }

    @Test
    @Disabled
    void updateUsernameById() {
    }

    @Test
    @Disabled
    void updatePasswordById() {
    }

    @Test
    @Disabled
    void deleteUserById() {
    }

    @Test
    @Disabled
    void loginByMailPassword() {
    }

    @Test
    @Disabled
    void getUserByMail() {
    }
}