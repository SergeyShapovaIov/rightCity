package com.example.rightCity.service.request;

import org.apache.http.client.methods.*;
import org.json.JSONObject;

import java.io.*;
import java.net.URISyntaxException;

/**
 * The type Request service.
 */
public class UserRequestService extends RequestService{
    /**
     * if you're hosting api on a local machine you should set address like
     * 192.168.**.**:port
     */
    private final String url = "http://192.168.31.173:8080/";


    /**
     * Send registration request.
     *
     * @param user User entity key-value as JSONObject
     * @return the response of request
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public String sendRegistrationRequest(JSONObject user) throws UnsupportedEncodingException {
        HttpPost request = buildPostRegistrationRequest(user);

        return sendRequest(request);
    }


    /**
     * Send login request string.
     *
     * @param user User entity key-value as JSONObject
     * @return the response of request
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public String sendLoginRequest(JSONObject user) throws UnsupportedEncodingException {
        HttpPost request = buildPostLoginRequest(user);

        return sendRequest(request);
    }


    /**
     * Send update username request string.
     *
     * @param user User entity key-value as JSONObject
     * @return the response of request
     */
    public String sendUpdateUsernameRequest(Long id, JSONObject user) throws UnsupportedEncodingException {
        HttpPut request = buildPutUpdateUsernameRequest(id, user);

        return sendRequest(request);
    }


    /**
     * Send update password request string.
     *
     * @return the response of request
     */
    public String sendUpdatePasswordRequest(Long id, JSONObject user) throws UnsupportedEncodingException{
        HttpPut request = buildPutUpdatePasswordRequest(id, user);

        return sendRequest(request);
    }


    /**
     * Send get user by email request string.
     *
     * @return the response of request
     */
    public String sendGetUserByEmailRequest(String email) throws URISyntaxException {
        HttpGet request = buildGetUserByEmailRequest(email);

        return sendRequest(request);
    }


    /**
     * Send delete user by id request string.
     *
     * @return the response of request
     */
    public String sendDeleteUserByIdRequest(Long id) throws URISyntaxException {
        HttpDelete request = buildDeleteUserByIdRequest(id);

        return sendRequest(request);
    }


    private HttpPost buildPostRegistrationRequest(JSONObject user) throws UnsupportedEncodingException {
        return buildPostRequest(user, "users/registration");
    }


    private HttpPost buildPostLoginRequest(JSONObject user) throws UnsupportedEncodingException {
        return buildPostRequest(user, "users/login");
    }


    private HttpPut buildPutUpdateUsernameRequest(Long id, JSONObject user) throws UnsupportedEncodingException {
        return buildPutRequest(user, "users/updateUsername?ID=".concat(String.valueOf(id)));
    }


    private HttpPut buildPutUpdatePasswordRequest(Long id, JSONObject user) throws UnsupportedEncodingException {
        return buildPutRequest(user, "users/updatePassword?ID=".concat(String.valueOf(id)));
    }


    private HttpGet buildGetUserByEmailRequest(String email) throws URISyntaxException {
        return buildGetRequest("users/getUserByMail", "mail", email);
    }


    private HttpDelete buildDeleteUserByIdRequest(Long id) throws URISyntaxException {
        return buildDeleteRequest("users/", "ID", String.valueOf(id));
    }
}
