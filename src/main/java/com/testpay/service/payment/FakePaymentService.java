package com.testpay.service.payment;

import com.testpay.model.notification.NotificationMessage;
import com.testpay.model.payment.PaymentRequest;
import com.testpay.model.response.PaymentResponse;
import com.testpay.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class FakePaymentService implements PaymentService {

    @Autowired
    private FakeResponseGenerator fakeResponseGenerator;

    @Autowired
    private NotificationService notificationService;

    @Override
    public PaymentResponse create(PaymentRequest paymentRequest) {
        PaymentResponse fakeResponse = fakeResponseGenerator.generate();
        NotificationMessage notificationMessage = NotificationMessage
                .fromPaymentRequestAndResponse(paymentRequest, fakeResponse);
        notificationService.register(notificationMessage, paymentRequest.getNotificationUrl());
        return fakeResponse;
    }

    @Component
    private static class FakeResponseGenerator {
        private static final List<String> STATE_ARR = Arrays.asList("created", "failed", "approved");

        private PaymentResponse generate() {
            return new PaymentResponse(generateId(), getTime(), generateState());
        }

        private String getTime() {
            return DateTimeFormatter.ISO_INSTANT.format(Instant.now());
        }

        private String generateId() {
            return new BigInteger(130, new SecureRandom()).toString(32);
        }

        private String generateState() {
            return STATE_ARR.get((int) (Math.random() * 3));
        }
    }
}
