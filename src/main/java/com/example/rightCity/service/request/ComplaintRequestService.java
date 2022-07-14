package com.example.rightCity.service.request;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

public class ComplaintRequestService extends RequestService {

    /**
     * TODO: test
     *
     * @return response
     */
    public String sendAddComplaintByUserId(Long userId, JSONObject complaint) throws UnsupportedEncodingException, URISyntaxException {
        HttpPost request = buildPostAddComplaintByUserIdRequest(userId, complaint);

        return sendRequest(request);
    }


    private HttpPost buildPostAddComplaintByUserIdRequest(Long userId, JSONObject complaint) throws UnsupportedEncodingException, URISyntaxException {
        URI uri = new URIBuilder()
                .setPath(url.concat("complain/addComplainByID"))
                .addParameter("ID", String.valueOf(userId))
                .build();

        return buildPostRequest(complaint, uri);
    }
}
