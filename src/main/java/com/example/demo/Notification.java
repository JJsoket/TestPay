package com.example.demo;

import com.example.demo.to.PayEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.UnsupportedEncodingException;

public class Notification extends Thread {
    private static final int RESEND_INTERVAL = 10_368 * 1000;
    private int resendCount;

    private PayEvent event;

    public Notification(PayEvent event) {
        this.event = event;
        resendCount = 25;
    }

    @Override
    public void run() {
        HttpClient httpclient = HttpClients.createDefault();
        HttpResponse response = null;
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        HttpPost httppost = new HttpPost(event.getNotification_url());
        StringEntity postingString = null;
        try {
            postingString = new StringEntity(gson.toJson(event));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        httppost.setEntity(postingString);
        httppost.setHeader("Content-type", "application/json");
        do {
            try {
                response = httpclient.execute(httppost);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (requestIsNotSuccess(response) && resendIsActual());
    }

    private boolean requestIsNotSuccess(HttpResponse response) {
        return response == null || response.getStatusLine().getStatusCode() != 200;
    }

    private boolean resendIsActual() {
        if (--resendCount > 0) {
            waitSomeTime();
            return true;
        }
        return false;
    }

    private void waitSomeTime() {
        try {
            Thread.sleep(RESEND_INTERVAL);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
