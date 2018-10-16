package com.example.demo;

import com.example.demo.to.ErrorResponse;
import com.example.demo.to.PayEvent;
import com.example.demo.to.Request;
import com.example.demo.to.FakeResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PayController {

    @PostMapping("/payments/payment")
    public FakeResponse createPayment(@RequestBody @Valid Request request) {
        FakeResponse fakeResponse = new FakeResponse();
        PayEvent event = new PayEvent(request.getNotification_url(),
                request.getTransaction().getAmount().getCurrency(),
                request.getTransaction().getAmount().getValue(),
                fakeResponse.getId(),
                request.getTransaction().getExternal_id(),
                fakeResponse.getState(), "");
        String Sha2sig = DigestUtils.sha256Hex(
                event.getCurrency() +
                        event.getAmount() +
                        event.getId() +
                        event.getExternal_id() +
                        event.getStatus());
        event.setSha2sig(Sha2sig);
        Notification notification = new Notification(event);
        notification.start();
        return fakeResponse;
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
