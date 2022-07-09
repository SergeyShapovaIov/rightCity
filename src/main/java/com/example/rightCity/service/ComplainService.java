package com.example.rightCity.service;

import com.example.rightCity.entity.ComplainEntity;
import com.example.rightCity.entity.UserEntity;
import com.example.rightCity.repository.ComplainRepo;
import com.example.rightCity.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class ComplainService {


    private final ComplainRepo complainRepo;
    private final UserRepo userRepo;

    public ComplainService(ComplainRepo complainRepo, UserRepo userRepo) {
        this.complainRepo = complainRepo;
        this.userRepo = userRepo;
    }

    public ComplainEntity addComplainByUserID(ComplainEntity complain, Long userId){
        UserEntity user = userRepo.findById(userId).get();
        complain.setUser(user);
        return complainRepo.save(complain);
    }
}
