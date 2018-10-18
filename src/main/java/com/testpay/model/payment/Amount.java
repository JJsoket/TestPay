package com.testpay.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class Amount {

    @NotNull
    @Length(max = 10)
    @DecimalMin(value = "1")
    @DecimalMax(value = "9999999999")
    @JsonProperty("value")
    private String value;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}")
    @JsonProperty("currency")
    private String currency;

    public Amount() {
    }

    public Amount(String value, String currency) {
        this.value = value;
        this.currency = currency;
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
