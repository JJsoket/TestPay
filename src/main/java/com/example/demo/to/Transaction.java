package com.example.demo.to;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Transaction {

    private String external_id;

    @NotNull
    @Valid
    private Amount amount;

    private String description;

    public Transaction() {
    }

    public String getExternal_id() {
        return external_id;
    }

    public void setExternal_id(String external_id) {
        this.external_id = external_id;
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
