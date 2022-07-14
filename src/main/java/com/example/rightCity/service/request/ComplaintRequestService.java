package com.example.rightCity.service.request;

import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class ComplaintRequestService extends RequestService {

    /**
     * TODO: test
     *
     * @return response
     */
    public String sendAddComplaintByUserId(Long userId, JSONObject complaint) throws UnsupportedEncodingException {
        HttpPost request = buildPostAddComplaintByUserIdRequest(userId, complaint);

        return sendRequest(request);
    }


    private HttpPost buildPostAddComplaintByUserIdRequest(Long userId, JSONObject complaint) throws UnsupportedEncodingException {
        return buildPostRequest(
                complaint,
                "complain/addComplainByID".concat(
                        "?ID=".concat(String.valueOf(userId))
                )
        );
    }
}
