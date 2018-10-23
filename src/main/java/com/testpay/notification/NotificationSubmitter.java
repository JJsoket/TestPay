package com.testpay.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testpay.model.notification.NotificationMessage;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

class NotificationSubmitter {
    private ObjectMapper mapper;
    private HttpClient httpClient;

    NotificationSubmitter() {
        this.mapper = new ObjectMapper();
        this.httpClient = HttpClients.createDefault();
    }

    public boolean submit(NotificationEvent notificationEvent) throws Exception {
        HttpPost httpPost = new HttpPost(notificationEvent.getNotificationUrl());
        String payload = buildPayload(notificationEvent.getMessage());

        httpPost.setEntity(new StringEntity(payload));
        httpPost.setHeader("Content-type", "application/json");

        try {
            HttpResponse response = httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    private String buildPayload(NotificationMessage notification) throws JsonProcessingException {
        return mapper.writeValueAsString(notification);
    }
}
