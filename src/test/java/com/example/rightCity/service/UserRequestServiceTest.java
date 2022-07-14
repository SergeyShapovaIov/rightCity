package com.example.rightCity.service;

import com.example.rightCity.service.request.UserRequestService;
import net.bytebuddy.utility.RandomString;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestServiceTest {

    @Test
    void sendRegistrationRequest() throws UnsupportedEncodingException {
        UserRequestService service = new UserRequestService();
        JSONObject testUser = new JSONObject();

        testUser.put("fio", RandomString.make().concat("FIO"));
        testUser.put("password", RandomString.make().concat("PASSWORD"));
        testUser.put("mail", RandomString.make().concat("@gmail.com"));

        String response = service.sendRegistrationRequest(testUser);

        assertEquals("User added", response);
    }

    @Test
    void sendLoginRequest() throws UnsupportedEncodingException {
        UserRequestService service = new UserRequestService();
        JSONObject testUser = new JSONObject();

        testUser.put("password", "hIdwdbNnPASSWORD");
        testUser.put("mail", "Yav7hVTB@gmail.com");

        String response = service.sendLoginRequest(testUser);

        assertEquals("Entry successful", response);
    }

    @Test
    void sendUpdateUsernameRequest() throws UnsupportedEncodingException {
        UserRequestService service = new UserRequestService();
        JSONObject user = new JSONObject();
        Long id = 2L;
        user.put("fio", "newNameFromTest".concat(RandomString.make()));

        String response = service.sendUpdateUsernameRequest(id, user);

        assertEquals("Username updated", response);
    }

    @Test
    void sendUpdatePasswordRequest() throws UnsupportedEncodingException {
        UserRequestService service = new UserRequestService();
        JSONObject user = new JSONObject();
        Long id = 2L;
        user.put("password", "newPasswordFromTest".concat(RandomString.make()));

        String response = service.sendUpdatePasswordRequest(id, user);

        assertEquals("Password updated", response);
    }

    @Test
    void sendGetUserByEmailRequest() throws URISyntaxException {
        UserRequestService service = new UserRequestService();

        String response = service.sendGetUserByEmailRequest("ADatSQ4T@gmail.com");

        JSONObject expected = new JSONObject();

        expected.put("fio", "hlGZdsYfFIO");
        expected.put("mail", "ADatSQ4T@gmail.com");
        expected.put("id", 24);
        expected.put("password", "JUHSWAk6PASSWORD");

        JSONObject actual = new JSONObject(response);

        assertEquals(expected.toString(), actual.toString());
    }
}