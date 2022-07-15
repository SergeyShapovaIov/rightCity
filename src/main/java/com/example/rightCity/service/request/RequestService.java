package com.example.rightCity.service.request;

import com.sun.istack.NotNull;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class RequestService {
    protected final String url = "http://192.168.31.173:8080/";


    protected HttpPost buildPostRequest(JSONObject body, String path) throws UnsupportedEncodingException {
        HttpPost httpPostRequest = new HttpPost(path);
        StringEntity params = new StringEntity(body.toString());

        httpPostRequest.addHeader("content-type", "application/json");
        httpPostRequest.setEntity(params);

        return httpPostRequest;
    }


    protected HttpPut buildPutRequest(JSONObject body, String path) throws UnsupportedEncodingException {
        HttpPut httpPutRequest = new HttpPut(url.concat(path));
        StringEntity params = new StringEntity(body.toString());

        httpPutRequest.addHeader("content-type", "application/json");
        httpPutRequest.setEntity(params);

        return httpPutRequest;
    }


    protected HttpGet buildGetRequest(String path, String parameter, String value) throws URISyntaxException {
        URI uri = new URIBuilder(url.concat(path))
                .addParameter(parameter, value)
                .build();

        return new HttpGet(uri);
    }


    protected HttpDelete buildDeleteRequest(String path, String parameter, String value) throws URISyntaxException {
        URI uri = new URIBuilder(url.concat(path))
                .addParameter(parameter, value)
                .build();

        return new HttpDelete(uri);
    }


    protected String sendRequest(HttpUriRequest request) {
        try {
            @NotNull
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(request);

            return responseToString(response);

        } catch (HttpHostConnectException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    protected String responseToString(HttpResponse response) throws IOException {
        @NotNull
        HttpEntity entity = response.getEntity();

        return entityToString(entity);
    }


    protected String entityToString(HttpEntity entity) throws IOException {
        try(InputStream inputStream = entity.getContent()) {
            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));
        }
    }
}
