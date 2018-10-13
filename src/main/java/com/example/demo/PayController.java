package com.example.demo;

import com.example.demo.to.ErrorResponse;
import com.example.demo.to.Request;
import com.example.demo.to.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PayController {

    @PostMapping("/payments/payment")
    public Response createPayment(@RequestBody @Valid Request request) {
        return new Response();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse validationException() {
        return new ErrorResponse("INVALID_REQUEST",
                "Request is not well-formatted, syntactically incorrect or violates schema");
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponse accessException() {
        return new ErrorResponse("AUTHENTICATION_FAILURE",
                "Authentication failed due to invalid authentication credentials");
    }

}
