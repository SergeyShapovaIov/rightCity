package com.example.rightCity.exception.user;

public class OldNameMatchesNewOneException extends RuntimeException {
    public OldNameMatchesNewOneException() {super ("The old name matches the new name");}
}
