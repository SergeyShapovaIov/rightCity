package com.example.rightCity.exception.user;

public class CombinationMailPasswordException extends RuntimeException{
    public CombinationMailPasswordException() {
        super("Incorrect user/password combination");
    }
}
