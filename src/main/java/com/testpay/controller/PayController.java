package com.testpay.controller;

import com.testpay.model.notification.NotificationMessage;
import com.testpay.model.payment.PaymentRequest;
import com.testpay.model.response.ErrorResponse;
import com.testpay.notification.NotificationEvent;
import com.testpay.notification.NotificationQueue;
import com.testpay.notification.signature.INotificationSigner;
import com.testpay.service.FakeResponseGeneratorService;
import com.testpay.model.response.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PayController {
    @Autowired
    private FakeResponseGeneratorService service;
    @Autowired
    private INotificationSigner notificationSigner;
    @Autowired
    private NotificationQueue notificationQueue;

    @PostMapping("/payments/payment")
    public PayResponse createPayment(@RequestBody @Valid PaymentRequest paymentRequest) {
        PayResponse fakeResponse = service.generateOne();
        NotificationMessage notificationMessage = NotificationMessage
                .fromPaymentRequestAndResponse(paymentRequest, fakeResponse);
        String notificationSignature = notificationSigner.generateSignature(notificationMessage);
        notificationMessage.setSha2sig(notificationSignature);

        NotificationEvent event = new NotificationEvent(paymentRequest.getNotificationUrl(),
                notificationMessage,
                NotificationEvent.DEFAULT_RETRIES,
                NotificationEvent.DEFAULT_TIMEOUT_SEC);

        notificationQueue.push(event);

        return fakeResponse;
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
