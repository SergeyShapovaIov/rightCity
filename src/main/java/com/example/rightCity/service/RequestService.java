package com.example.rightCity.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequestService {
    private final String url = "http://192.168.31.173:8080/";
    private String registrationContent;

    public void sendRegistrationRequest() {

        HttpClient client = HttpClientBuilder.create().build();

        JSONObject user = new JSONObject();
        user.put("fio", "fioViaJson");
        user.put("mail", "mailViaJson");
        user.put("password", "passwordViaJson");

        try {
            HttpPost request = new HttpPost(url.concat("users/registration"));
            StringEntity params = new StringEntity(user.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = client.execute(request);


            var entity = response.getEntity();
            if (entity != null) {
                try(InputStream inputStream = entity.getContent()) {
                    registrationContent = new BufferedReader(new InputStreamReader(inputStream))
                            .lines().collect(Collectors.joining("\n"));;
                }
            }
        } catch (HttpHostConnectException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getRegistrationContent() {
        return registrationContent;
    }
}
