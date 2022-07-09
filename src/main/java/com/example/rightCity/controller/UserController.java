package com.example.rightCity.controller;

import com.example.rightCity.entity.UserEntity;
import com.example.rightCity.exception.UserWithMailAlreadyExistException;
import com.example.rightCity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody UserEntity user){
        try {
           userService.registration(user);
           return ResponseEntity.ok("User added");
        }
        catch (UserWithMailAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error!");
        }

    }

    @GetMapping("/")
    public ResponseEntity getUsers(){
        try {
            return ResponseEntity.ok("User Good!");
        } catch ( Exception e) {
            return ResponseEntity.badRequest().body("User Error!");
        }
    }
}
