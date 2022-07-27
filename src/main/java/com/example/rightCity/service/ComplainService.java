package com.example.rightCity.service;

import com.example.rightCity.entity.ComplainEntity;
import com.example.rightCity.exception.complain.ComplainNotFoundException;
import com.example.rightCity.exception.user.UserNotFoundException;
import com.example.rightCity.repository.ComplainRepository;
import com.example.rightCity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;


/**
 * The type Complain service.
 *
 * TODO: test required
 */
@Service
public class ComplainService {
    private final ComplainRepository complainRepository;
    private final UserRepository userRepository;


    public ComplainService(ComplainRepository complainRepository, UserRepository userRepository) {
        this.complainRepository = complainRepository;
        this.userRepository = userRepository;
    }


    public ComplainEntity addComplainByUserId(ComplainEntity complain, Long userId) {
        final AtomicReference<ComplainEntity> saved = new AtomicReference<>();

        userRepository
                .findById(userId)
                        .ifPresentOrElse(user -> {
                            complain.setUser(user);
                            saved.set(complainRepository.save(complain));
                        },
                                UserNotFoundException::new
                        );

        return saved.get();
    }


    public ComplainEntity getComplainById(Long id) {
        return complainRepository
                .findById(id)
                .orElseThrow(ComplainNotFoundException::new);
    }


    public void deleteComplainById(Long id){
        complainRepository
                .findById(id)
                        .ifPresentOrElse(
                                complain -> complainRepository.deleteById(complain.getID()),
                                ComplainNotFoundException::new
                        );
    }
}
