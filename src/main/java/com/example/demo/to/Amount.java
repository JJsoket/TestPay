package com.example.demo.to;

import javax.validation.constraints.*;

public class Amount {

    @NotNull
    @DecimalMin(value = "1")
    @DecimalMax(value = "9999999999")
    private String value;

    @NotBlank
    @Pattern(regexp = "USD|RUB|EUR")
    private String currency;

    public Amount() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
