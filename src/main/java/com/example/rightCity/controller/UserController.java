package com.example.rightCity.controller;

import com.example.rightCity.entity.UserEntity;
import com.example.rightCity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;
    @PostMapping
    public ResponseEntity addUser(@RequestBody UserEntity user){
        try {
            userRepo.save(user);
            return ResponseEntity.ok("User added");
        } catch ( Exception e) {
            return ResponseEntity.badRequest().body("Error!");
        }
    }

    @GetMapping("/")
    public ResponseEntity getUsers(){
        try {
            return ResponseEntity.ok("Good!");
        } catch ( Exception e) {
            return ResponseEntity.badRequest().body("Error!");
        }
    }
}
