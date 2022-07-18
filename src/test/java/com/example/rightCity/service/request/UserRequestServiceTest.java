package com.example.rightCity.service.request;

import net.bytebuddy.utility.RandomString;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserRequestServiceTest {

    @Test
    void sendRegistrationRequest()
            throws UnsupportedEncodingException, URISyntaxException {
        UserRequestService service = new UserRequestService();
        JSONObject testUser = new JSONObject();

        testUser.put("fio", RandomString.make().concat("FIO"));
        testUser.put("password", RandomString.make().concat("PASSWORD"));
        testUser.put("mail", RandomString.make().concat("@gmail.com"));

        String response = service.sendRegistrationRequest(testUser);

        assertEquals("User added", response);
    }

    @Test
    void sendLoginRequest() throws UnsupportedEncodingException, URISyntaxException {
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

        HttpResponse response = service.sendGetUserByEmailRequestAndGetResponse("ADatSQ4T@gmail.com");
        StatusLine statusLine = mock(response.getStatusLine().getClass());
        when(statusLine.getStatusCode()).thenReturn(200);
    }

    @Test
    void sendDeleteUserByIdRequest() {
    }
}