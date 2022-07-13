package com.example.rightCity.service;

import com.sun.istack.NotNull;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.*;
import java.util.stream.Collectors;

/**
 * The type Request service.
 */
public class UserRequestService {
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
     * TODO: test
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
     * TODO: test
     *
     * @param user User entity key-value as JSONObject
     * @return the response of request
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public String sendUpdateUsernameRequest(JSONObject user) throws UnsupportedEncodingException {
        HttpPut request = buildPutUpdateUsernameRequest(user);

        return sendRequest(request);
    }


    /**
     * Send update password request string.
     * TODO: test
     *
     * @param user User entity key-value as JSONObject
     * @return the response of request
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public String sendUpdatePasswordRequest(JSONObject user) throws UnsupportedEncodingException{
        HttpPut request = buildPutUpdatePasswordRequest(user);

        return sendRequest(request);
    }


    /**
     * Send get user by email request string.
     * TODO: test, не ручаюсь за этот кусок кода
     *
     * @param user User entity key-value as JSONObject
     * @return the response of request
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public String sendGetUserByEmailRequest(JSONObject user) throws UnsupportedEncodingException {
        HttpGet request = buildGetUserByEmailRequest(user);

        return sendRequest(request);
    }


    private String sendRequest(HttpUriRequest request) {
        try {
            @NotNull
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(request);

            return registrationResponseToString(response);

        } catch (HttpHostConnectException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    private String registrationResponseToString(HttpResponse response) throws IOException {
        @NotNull
        HttpEntity entity = response.getEntity();

        return entityToString(entity);
    }


    private String entityToString(HttpEntity entity) throws IOException {
        try(InputStream inputStream = entity.getContent()) {
            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));
        }
    }


    private HttpPost buildPostRegistrationRequest(JSONObject user) throws UnsupportedEncodingException {
        return buildPostRequest(user, "users/registration");
    }


    private HttpPost buildPostLoginRequest(JSONObject user) throws UnsupportedEncodingException {
        return buildPostRequest(user, "users/login");
    }


    private HttpPost buildPostRequest(JSONObject data, String request) throws UnsupportedEncodingException {
        HttpPost httpPostRequest = new HttpPost(url.concat(request));
        StringEntity params = new StringEntity(data.toString());

        httpPostRequest.addHeader("content-type", "application/json");
        httpPostRequest.setEntity(params);

        return httpPostRequest;
    }


    private HttpPut buildPutUpdateUsernameRequest(JSONObject user) throws UnsupportedEncodingException {
        return buildPutRequest(user, "users/updateUsername");
    }


    private HttpPut buildPutUpdatePasswordRequest(JSONObject user) throws UnsupportedEncodingException {
        return buildPutRequest(user, "users/updatePassword");
    }



    private HttpPut buildPutRequest(JSONObject data, String request) throws UnsupportedEncodingException {
        HttpPut httpPutRequest = new HttpPut(url.concat(request));
        StringEntity params = new StringEntity(data.toString());

        httpPutRequest.addHeader("content-type", "application/json");
        httpPutRequest.setEntity(params);

        return httpPutRequest;
    }


    private HttpGet buildGetUserByEmailRequest(JSONObject user) throws UnsupportedEncodingException{
        return buildGetRequest(user, "users/getUserByMail");
    }


    private HttpGet buildGetRequest(JSONObject data, String request) throws UnsupportedEncodingException {
        HttpGet httpGetRequest = new HttpGet(url.concat(request));

        httpGetRequest.addHeader("content-type", "application/json");

        return httpGetRequest;
    }
}
