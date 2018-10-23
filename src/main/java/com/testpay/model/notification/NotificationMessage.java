package com.testpay.model.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testpay.model.payment.PaymentRequest;
import com.testpay.model.response.PaymentResponse;

public class NotificationMessage {
    @JsonProperty("currency")
    private String currency;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("id")
    private String id;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("sha2sig")
    private String sha2sig;

    public NotificationMessage() {
    }

    public static NotificationMessage fromPaymentRequestAndResponse(PaymentRequest request,
                                                                    PaymentResponse response) {
        return new NotificationMessage()
                .setAmount(request.getTransaction().getAmount().getValue())
                .setCurrency(request.getTransaction().getAmount().getCurrency())
                .setStatus(response.getState())
                .setExternalId(request.getTransaction().getExternalId())
                .setId(response.getId());
    }

    public String getCurrency() {
        return currency;
    }

    public NotificationMessage setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public NotificationMessage setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public String getId() {
        return id;
    }

    public NotificationMessage setId(String id) {
        this.id = id;
        return this;
    }

    public String getExternalId() {
        return externalId;
    }

    public NotificationMessage setExternalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public NotificationMessage setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getSha2sig() {
        return sha2sig;
    }

    public NotificationMessage setSha2sig(String sha2sig) {
        this.sha2sig = sha2sig;
        return this;
    }
}
