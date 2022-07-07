package com.example.rightCity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/complain")
public class ComplainController {

    @GetMapping("/")
    public ResponseEntity getUsers(){
        try {
            return ResponseEntity.ok("Complain Good!");
        } catch ( Exception e) {
            return ResponseEntity.badRequest().body("Complain Error!");
        }
    }
}
