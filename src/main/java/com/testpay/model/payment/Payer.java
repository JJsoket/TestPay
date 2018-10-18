package com.testpay.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Payer {

    @NotBlank
    @Email()
    @JsonProperty("email")
    private String email;

    public Payer() {
    }

    public Payer(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
