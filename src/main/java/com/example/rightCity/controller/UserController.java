package com.example.rightCity.controller;

import com.example.rightCity.entity.UserEntity;
import com.example.rightCity.exception.user.CombinationMailPasswordException;
import com.example.rightCity.exception.user.OldNameMatchesNewOneException;
import com.example.rightCity.exception.user.UserNotFoundException;
import com.example.rightCity.exception.user.UserWithMailAlreadyExistException;
import com.example.rightCity.service.UserService;
import com.example.rightCity.util.UserMessage;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> addUser(@RequestBody @NonNull UserEntity user){
        try {
            userService.registration(user);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(UserMessage.USER_DELETED);

        } catch (UserWithMailAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Empty request!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error!");
        }

    }


    @PostMapping ("/login")
    public ResponseEntity<?> loginByMailPassword(@RequestBody @NonNull UserEntity user) {
        try {
            userService.loginByMailPassword(user);

            return ResponseEntity.ok(UserMessage.ENTRY_SUCCESSFUL);

        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (CombinationMailPasswordException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Something went wrong...");
        }
    }


    @PutMapping("/updateUsername")
    public ResponseEntity<?> updateUsernameUserById(@RequestBody @NonNull UserEntity user,
                                                 @RequestParam @NonNull Long ID) {
        try{
            userService.updateUsernameById(user.getFIO(), ID);

            return ResponseEntity.accepted().body(UserMessage.USERNAME_UPDATED);

        } catch (OldNameMatchesNewOneException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error!");
        }
    }


    @PutMapping ("/updatePassword")
    public ResponseEntity<?> updatePasswordById(@RequestBody @NonNull UserEntity user,
                                             @RequestParam @NonNull Long ID) {
        try{
            userService.updatePasswordById(user.getPassword(), ID);

            return ResponseEntity.ok(UserMessage.PASSWORD_UPDATED);

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error!");
        }
    }


    @DeleteMapping("/{ID}")
    public ResponseEntity<?> deleteUserById(@PathVariable @NonNull Long ID) {
        try {
            userService.deleteUserById(ID);

            return ResponseEntity.ok(UserMessage.USER_DELETED);

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Delete error!");
        }
    }


    @GetMapping("/getUserByMail")
    public ResponseEntity<?> getUserByMail(@RequestParam @NonNull String mail) {
        try{
            return ResponseEntity.ok(userService.getUserByMail(mail));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Find error!");
        }
    }
}
