package com.example.rightCity.exception.user;

public class UserWithMailAlreadyExistException extends RuntimeException{
    public UserWithMailAlreadyExistException() {
        super ("User with this email is already exist!");
    }
}
