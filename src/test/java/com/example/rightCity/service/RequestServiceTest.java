package com.example.rightCity.service;

import net.bytebuddy.utility.RandomString;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestServiceTest {

    @Test
    void sendRegistrationRequest() {
        RequestService service = new RequestService();
        JSONObject testUser = new JSONObject();

        testUser.put("fio", RandomString.make().concat("FIO"));
        testUser.put("password", RandomString.make().concat("PASSWORD"));
        testUser.put("mail", RandomString.make().concat("@gmail.com"));

        service.sendRegistrationRequest(testUser);

        String response = service.getRegistrationResponse();
        assertEquals("User added", response);
    }
}