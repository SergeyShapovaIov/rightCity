package com.example.rightCity.service;

import com.sun.istack.NotNull;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.*;
import java.util.stream.Collectors;

/**
 * The type Request service.
 */
public class RequestService {
    /**
     * if you're hosting api on a local machine you should set address like
     * 192.168.**.**:port
     */
    private final String url = "http://192.168.31.173:8080/";
    private String registrationResponse;


    /**
     * Send registration request.
     * TODO: test me
     * @param user User entity key-value
     */
    public void sendRegistrationRequest(JSONObject user) {
        try {
            sendRequest(user);
        } catch (HttpHostConnectException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendRequest(JSONObject user) throws IOException {
        @NotNull
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = buildRegistrationRequest(user);
        HttpResponse response = client.execute(request);

        saveRegistrationResponse(response);
    }


    private void saveRegistrationResponse(HttpResponse response) throws IOException {
        @NotNull
        HttpEntity entity = response.getEntity();

        registrationResponse = entityToString(entity);
    }


    private String entityToString(HttpEntity entity) throws IOException {
        try(InputStream inputStream = entity.getContent()) {
            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));
        }
    }


    private HttpPost buildRegistrationRequest(JSONObject user) throws UnsupportedEncodingException {
        return buildPostRequest(user, "users/registration");
    }


    private HttpPost buildPostRequest(JSONObject data, String request) throws UnsupportedEncodingException {
        HttpPost httpPostRequest = new HttpPost(url.concat(request));
        StringEntity params = new StringEntity(data.toString());

        httpPostRequest.addHeader("content-type", "application/json");
        httpPostRequest.setEntity(params);

        return httpPostRequest;
    }


    /**
     * Gets registration response.
     *
     * @return the registration response
     */
    public String getRegistrationResponse() {
        return registrationResponse;
    }
}
