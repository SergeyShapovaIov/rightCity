package com.example.rightCity.repository;

import com.example.rightCity.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByMail() {
        // given
        String email = "T2qPpuw7@gmail.com";

        UserEntity expected = new UserEntity();

        expected.setID(26L);
        expected.setFIO("XrUUdMMNFIO");
        expected.setMail(email);
        expected.setPassword("0uCOQvqKPASSWORD");

        //when
        UserEntity actual = userRepository.findByMail(email);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}