package com.example.rightCity.service.request;

import net.bytebuddy.utility.RandomString;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import org.apache.logging.log4j.LogManager;

import static org.assertj.core.api.Assertions.assertThat;

class UserRequestServiceTest {

    private final Logger logger = LogManager.getLogger(UserRequestServiceTest.class);

    @Test
    void sendRegistrationRequest()
            throws UnsupportedEncodingException, URISyntaxException {
        UserRequestService service = new UserRequestService();
        JSONObject testUser = new JSONObject();

        testUser.put("fio", RandomString.make().concat("FIO"));
        testUser.put("password", RandomString.make().concat("PASSWORD"));
        testUser.put("mail", RandomString.make().concat("@gmail.com"));

        HttpResponse response = service.sendRegistrationRequestAndGetResponse(testUser);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
    }

    @Test
    void sendLoginRequest() throws UnsupportedEncodingException, URISyntaxException {
        UserRequestService service = new UserRequestService();
        JSONObject testUser = new JSONObject();

        testUser.put("password", "hIdwdbNnPASSWORD");
        testUser.put("mail", "Yav7hVTB@gmail.com");

        String response = service.sendLoginRequest(testUser);

        assertThat(response).isEqualTo("Entry successful");
    }

    @Test
    void sendUpdateUsernameRequest() throws UnsupportedEncodingException, URISyntaxException {
        UserRequestService service = new UserRequestService();
        JSONObject user = new JSONObject();
        Long id = 2L;
        user.put("fio", "newNameFromTest".concat(RandomString.make()));

        String response = service.sendUpdateUsernameRequest(id, user);

        assertThat(response).isEqualTo("Username updated");
    }

    @Test
    void sendUpdatePasswordRequest() throws UnsupportedEncodingException, URISyntaxException {
        UserRequestService service = new UserRequestService();
        JSONObject user = new JSONObject();
        Long id = 2L;
        user.put("password", "newPasswordFromTest".concat(RandomString.make()));

        String response = service.sendUpdatePasswordRequestAndGetResponseAsString(id, user);

        assertThat(response).isEqualTo("Password updated");
    }

    @Test
    void sendGetUserByEmailRequest() throws URISyntaxException {
        UserRequestService service = new UserRequestService();

        HttpResponse response = service.sendUserByEmailRequestAndGetResponse("ADatSQ4T@gmail.com");

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
    }

    @Test
    void sendDeleteUserByIdRequest() {
    }
}