package com.example.demo;

import com.example.demo.to.Request;
import com.example.demo.to.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PayController {

    @PostMapping("/payments/payment")
    public Response createPayment(@RequestBody @Valid Request request) {
        return new Response(
                PayUtil.generateId(),
                PayUtil.getTime(),
                PayUtil.generateState()
        );
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String validationException() {
        return "{\n" +
                "    \"error\": \"400\",\n" +
                "    \"error_description\": \"Request is not well-formatted, syntactically incorrect or violates schema\"\n" +
                "}";
    }

}
