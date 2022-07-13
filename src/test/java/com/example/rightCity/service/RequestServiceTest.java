package com.example.rightCity.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RequestServiceTest {

    @Test
    void sendRegistrationRequest() {
        RequestService service = new RequestService();

        service.sendRegistrationRequest();
        String content = service.getRegistrationContent();

        System.out.println("content = " + content);
    }
}