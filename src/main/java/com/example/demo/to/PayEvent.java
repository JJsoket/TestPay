package com.example.demo.to;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

public class PayEvent {

    private String notification_url;

    @Expose
    private String currency;
    @Expose
    private String amount;
    @Expose
    private String id;
    @Expose
    private String external_id;
    @Expose
    private String status;
    @Expose
    private String sha2sig;

    public PayEvent() {
    }

    public PayEvent(String notification_url, String currency, String amount, String id, String external_id, String status, String sha2sig) {
        this.notification_url = notification_url;
        this.currency = currency;
        this.amount = amount;
        this.id = id;
        this.external_id = external_id;
        this.status = status;
        this.sha2sig = sha2sig;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSha2sig() {
        return sha2sig;
    }

    public void setSha2sig(String sha2sig) {
        this.sha2sig = sha2sig;
    }

    @JsonIgnore
    public String getNotification_url() {
        return notification_url;
    }

    public void setNotification_url(String notification_url) {
        this.notification_url = notification_url;
    }
}
