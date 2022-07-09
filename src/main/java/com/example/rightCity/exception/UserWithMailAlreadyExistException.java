package com.example.rightCity.exception;

public class UserWithMailAlreadyExistException extends Exception{
    public UserWithMailAlreadyExistException(String message) {
        super (message);
    }
}
