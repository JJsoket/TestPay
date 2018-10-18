package com.testpay.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorResponse {
    INVALID_REQUEST("INVALID_REQUEST", "PaymentRequest is not well-formatted, syntactically incorrect or violates schema"),
    AUTHENTICATION_FAILURE("AUTHENTICATION_FAILURE", "Authentication failed due to invalid authentication credentials"),
    UNSUPPORTED_MEDIA_TYPE("UNSUPPORTED_MEDIA_TYPE", "The server does not support the request payload media type"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "An internal server error has occurred");

    @JsonProperty("error")
    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

    ErrorResponse(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

}
