package com.example.rightCity.controller;

import com.example.rightCity.entity.UserEntity;
import com.example.rightCity.exception.OldNameMatchesNewNameException;
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

    @PostMapping("/registration")
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
    public ResponseEntity updateUsernameUserById(@RequestParam String username, @RequestParam Long ID){
        try{
            userService.updateUsernameById(username,ID);
            return ResponseEntity.ok("Username updated");
        } catch (OldNameMatchesNewNameException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error!");
        }
    }
    @PutMapping ("/updatePassword")
    public ResponseEntity updatePasswordById(@RequestParam String password, @RequestParam Long ID){
        try{
            userService.updatePasswordByID(password, ID);
            return ResponseEntity.ok("Password updated");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error!");
        }
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity deleteUserById(@PathVariable Long ID){
        try {
            userService.deleteUserByID(ID);
            return ResponseEntity.ok("User deleted");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Delete error!");
        }
    }

    @GetMapping("/getUserByMail")
    public ResponseEntity getUserByMail(@RequestParam String mail){
        try{
            return ResponseEntity.ok(userService.getUserByMail(mail));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Find error!");
        }
    }
}
