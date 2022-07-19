package com.example.rightCity.service.request;

import lombok.NonNull;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RequestService {
    /**
     * if you're hosting api on a local machine you should set address like
     * 192.168.**.**:port
     */
    protected final String url = "http://192.168.1.234:8080/";


    protected HttpPost buildPostRequest(@NonNull JSONObject body, @NonNull URI request)
            throws UnsupportedEncodingException {

        HttpPost httpPostRequest = new HttpPost(request);
        StringEntity params = new StringEntity(body.toString());

        httpPostRequest.addHeader("content-type", "application/json");
        httpPostRequest.setEntity(params);

        return httpPostRequest;
    }



    protected HttpPost buildPostRequest(@NonNull JSONObject body, @NonNull String path)
            throws UnsupportedEncodingException, URISyntaxException {

        URI uri = new URIBuilder()
                .setPath(url.concat(path))
                .build();

        HttpPost postRequest = new HttpPost(uri);
        StringEntity params = new StringEntity(body.toString());

        postRequest.addHeader("content-type", "application/json");
        postRequest.setEntity(params);

        return postRequest;
    }


    protected HttpPut buildPutRequest(@NonNull JSONObject body,
                                      @NonNull String path,
                                      @NonNull String parameter,
                                      @NonNull String value)
            throws UnsupportedEncodingException, URISyntaxException {

        URI uri = new URIBuilder()
                .setPath(url.concat(path))
                .setParameter(parameter, value)
                .build();

        HttpPut putRequest = new HttpPut(uri);
        StringEntity params = new StringEntity(body.toString());

        putRequest.addHeader("content-type", "application/json");
        putRequest.setEntity(params);

        return putRequest;
    }


    protected HttpGet buildGetRequest(@NonNull String path, @NonNull String parameter, @NonNull String value)
            throws URISyntaxException {

        URI uri = new URIBuilder(url.concat(path))
                .addParameter(parameter, value)
                .build();

        return new HttpGet(uri);
    }


    protected HttpDelete buildDeleteRequest(@NonNull String path, @NonNull String parameter, @NonNull String value)
            throws URISyntaxException {

        URI uri = new URIBuilder(url.concat(path))
                .addParameter(parameter, value)
                .build();

        return new HttpDelete(uri);
    }


    protected String getResponseFromRequestAsString(@NonNull HttpUriRequest request) {
        return responseToString(getResponseFromRequest(request));
    }


    protected HttpResponse getResponseFromRequest(@NonNull HttpUriRequest request) {
        try {
            @NonNull
            HttpClient client = HttpClientBuilder.create().build();

            return client.execute(request);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }


    protected String responseToString(HttpResponse response) {
        @NonNull
        HttpEntity entity = response.getEntity();

        return entityToString(entity);
    }


    protected String entityToString(HttpEntity entity) {
        try(InputStream inputStream = entity.getContent()) {

            return new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return entity.toString();
    }
}
