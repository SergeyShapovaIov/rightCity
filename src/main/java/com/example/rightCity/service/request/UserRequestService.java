package com.example.rightCity.service.request;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.json.JSONObject;

import java.io.*;
import java.net.URISyntaxException;

/**
 * The type Request service.
 */
public class UserRequestService extends RequestService {
    /**
     * Send registration request.
     *
     * @param user User entity key-value as JSONObject
     * @return the response of request
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public String sendRegistrationRequestAndGetResponseAsString(JSONObject user)
            throws UnsupportedEncodingException, URISyntaxException {

        HttpPost request = buildRegistrationRequest(user);

        return getResponseFromRequestAsString(request);
    }


    public HttpResponse sendRegistrationRequestAndGetResponse(JSONObject user)
            throws UnsupportedEncodingException, URISyntaxException {

        HttpPost request = buildRegistrationRequest(user);

        return getResponseFromRequest(request);
    }


    /**
     * Send login request string.
     *
     * @param user User entity key-value as JSONObject
     * @return the response of request as string
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public String sendLoginRequest(JSONObject user)
            throws UnsupportedEncodingException, URISyntaxException {

        HttpPost request = buildLoginRequest(user);

        return getResponseFromRequestAsString(request);
    }


    /**
     * Send update username request string.
     *
     * @param user User entity key-value as JSONObject
     * @return the response of request
     */
    public String sendUpdateUsernameRequest(Long id, JSONObject user)
            throws UnsupportedEncodingException, URISyntaxException {
        HttpPut request = buildUpdateUsernameRequest(id, user);

        return getResponseFromRequestAsString(request);
    }


    public HttpResponse sendUpdatePasswordRequestAndGetResponse(Long id, JSONObject user)
            throws UnsupportedEncodingException, URISyntaxException {

        HttpPut request = buildUpdatePasswordRequest(id, user);

        return getResponseFromRequest(request);
    }


    public String sendUpdatePasswordRequestAndGetResponseAsString(Long id, JSONObject user)
            throws UnsupportedEncodingException, URISyntaxException {

        HttpPut request = buildUpdatePasswordRequest(id, user);

        return getResponseFromRequestAsString(request);
    }


    /**
     * Send get user by email request string.
     *
     * @return the response of request
     */
    public String sendUserByEmailRequestAndGetResponseAsString(String email) throws URISyntaxException {
        HttpGet request = buildGetUserByEmailRequest(email);

        return getResponseFromRequestAsString(request);
    }


    public HttpResponse sendUserByEmailRequestAndGetResponse(String email) throws URISyntaxException {
        HttpGet request = buildGetUserByEmailRequest(email);

        return getResponseFromRequest(request);
    }


    /**
     * Send delete user by id request string.
     *
     * @return the response of request
     */
    public String sendDeleteUserByIdRequest(Long id) throws URISyntaxException {
        HttpDelete request = buildDeleteUserByIdRequest(id);

        return getResponseFromRequestAsString(request);
    }


    private HttpPost buildRegistrationRequest(JSONObject user)
            throws UnsupportedEncodingException, URISyntaxException {

        return buildPostRequest(user, "users/registration");
    }


    private HttpPost buildLoginRequest(JSONObject user)
            throws UnsupportedEncodingException, URISyntaxException {

        return buildPostRequest(user, "users/login");
    }


    private HttpPut buildUpdateUsernameRequest(Long id, JSONObject user)
            throws UnsupportedEncodingException, URISyntaxException {

        return buildPutRequest(user, "users/updateUsername", "ID", id.toString());
    }


    private HttpPut buildUpdatePasswordRequest(Long id, JSONObject user)
            throws UnsupportedEncodingException, URISyntaxException {

        return buildPutRequest(user, "users/updatePassword", "ID", id.toString());
    }


    private HttpGet buildGetUserByEmailRequest(String email) throws URISyntaxException {

        return buildGetRequest("users/getUserByMail", "mail", email);
    }


    private HttpDelete buildDeleteUserByIdRequest(Long id) throws URISyntaxException {
        return buildDeleteRequest("users/", "ID", String.valueOf(id));
    }
}
