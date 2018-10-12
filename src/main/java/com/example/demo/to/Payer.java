package com.example.demo.to;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Payer {

    @NotBlank
    @Email()
    private String email;

    public Payer() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
