package com.testpay.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PaymentRequest {

    @NotBlank
    @Pattern(regexp = "order")
    @JsonProperty("intent")
    private String intent;

    @NotBlank
    @Pattern(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]") //URL
    @JsonProperty("notification_url")
    private String notificationUrl;

    @NotNull
    @Valid
    @JsonProperty("payer")
    private Payer payer;

    @NotNull
    @Valid
    @JsonProperty("transaction")
    private Transaction transaction;

    public PaymentRequest() {
    }

    public PaymentRequest(String intent, String notificationUrl, Payer payer, Transaction transaction) {
        this.intent = intent;
        this.notificationUrl = notificationUrl;
        this.payer = payer;
        this.transaction = transaction;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
