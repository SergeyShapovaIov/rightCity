package com.example.rightCity.service.request;

import com.sun.istack.NotNull;
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
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RequestService {
    /**
     * if you're hosting api on a local machine you should set address like
     * 192.168.**.**:port
     */
    protected final String url = "http://192.168.1.234:8080/";


    protected HttpPost buildPostRequest(@NotNull JSONObject body, @NotNull URI request)
            throws UnsupportedEncodingException {

        HttpPost httpPostRequest = new HttpPost(request);
        StringEntity params = new StringEntity(body.toString());

        httpPostRequest.addHeader("content-type", "application/json");
        httpPostRequest.setEntity(params);

        return httpPostRequest;
    }



    protected HttpPost buildPostRequest(@NotNull JSONObject body, @NotNull String path)
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


    protected HttpPut buildPutRequest(@NotNull JSONObject body, @NotNull String path)
            throws UnsupportedEncodingException {

        HttpPut putRequest = new HttpPut(url.concat(path));
        StringEntity params = new StringEntity(body.toString());

        putRequest.addHeader("content-type", "application/json");
        putRequest.setEntity(params);

        return putRequest;
    }


    protected HttpGet buildGetRequest(@NotNull String path, @NotNull String parameter, @NotNull String value)
            throws URISyntaxException {

        URI uri = new URIBuilder(url.concat(path))
                .addParameter(parameter, value)
                .build();

        return new HttpGet(uri);
    }


    protected HttpDelete buildDeleteRequest(@NotNull String path, @NotNull String parameter, @NotNull String value)
            throws URISyntaxException {

        URI uri = new URIBuilder(url.concat(path))
                .addParameter(parameter, value)
                .build();

        return new HttpDelete(uri);
    }


    protected String getResponseFromRequestAsString(@NotNull HttpUriRequest request) {
        return responseToString(getResponseFromRequest(request));
    }


    protected HttpResponse getResponseFromRequest(@NotNull HttpUriRequest request) {
        try {
            @NotNull
            HttpClient client = HttpClientBuilder.create().build();

            return client.execute(request);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }


    protected String responseToString(HttpResponse response) {
        @NotNull
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
