package com.testpay.controller;

import com.testpay.model.payment.PaymentRequest;
import com.testpay.model.response.ErrorResponse;
import com.testpay.model.response.PaymentResponse;
import com.testpay.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payments/payment")
    public PaymentResponse createPayment(@RequestBody @Valid PaymentRequest paymentRequest) {
        PaymentResponse response = paymentService.create(paymentRequest);
        return response;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse invalidRequest() {
        return ErrorResponse.INVALID_REQUEST;
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponse authenticationFailure() {
        return ErrorResponse.AUTHENTICATION_FAILURE;
    }

    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ErrorResponse unsupportedMediaType() {
        return ErrorResponse.UNSUPPORTED_MEDIA_TYPE;
    }


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse internalServerError() {
        return ErrorResponse.INTERNAL_SERVER_ERROR;
    }
}
