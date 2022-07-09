package com.example.rightCity.controller;

import com.example.rightCity.entity.UserEntity;
import com.example.rightCity.exception.OldNameMatchesNewName;
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

    @PostMapping("/regestration")
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

    @PutMapping("/updateUsername")
    public ResponseEntity updateUsernamedUserByID (@RequestParam String username, @RequestParam Long ID){
        try{
            userService.updateUsernameByID(username,ID);
            return ResponseEntity.ok("Username updated");
        } catch (OldNameMatchesNewName e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error!");
        }
    }
    @PutMapping ("/updatePassword")
    public ResponseEntity updatePasswordByID(@RequestParam String password, @RequestParam Long ID){
        try{
            userService.updatePasswordByID(password, ID);
            return ResponseEntity.ok("Password updated");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error!");
        }
    }
}
