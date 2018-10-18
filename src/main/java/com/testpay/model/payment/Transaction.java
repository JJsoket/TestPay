package com.testpay.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Transaction {

    @JsonProperty("external_id")
    private String externalId;

    @NotNull
    @Valid
    @JsonProperty("amount")
    private Amount amount;

    @JsonProperty("description")
    private String description;

    public Transaction() {
    }

    public Transaction(String externalId, Amount amount, String description) {
        this.externalId = externalId;
        this.amount = amount;
        this.description = description;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
