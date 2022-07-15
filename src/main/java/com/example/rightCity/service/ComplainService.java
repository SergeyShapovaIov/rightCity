package com.example.rightCity.service;

import com.example.rightCity.entity.ComplainEntity;
import com.example.rightCity.entity.UserEntity;
import com.example.rightCity.repository.ComplainRepository;
import com.example.rightCity.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ComplainService {


    private final ComplainRepository complainRepository;
    private final UserRepo userRepo;

    public ComplainService(ComplainRepository complainRepository, UserRepo userRepo) {
        this.complainRepository = complainRepository;
        this.userRepo = userRepo;
    }


    public ComplainEntity addComplainByUserId(ComplainEntity complain, Long userId){
        UserEntity user = userRepo
                .findById(userId)
                .orElseThrow(NoSuchElementException::new);

        complain.setUser(user);

        return complainRepository.save(complain);
    }


    public ComplainEntity getComplainById(Long id) {
        return complainRepository
                .findById(id)
                .orElseThrow(NoSuchElementException::new);
    }


    public void deleteComplainById(Long id){
        complainRepository
                .findById(id)
                .orElseThrow(NoSuchElementException::new);

        complainRepository.deleteById(id);
    }
}
