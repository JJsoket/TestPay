package com.testpay.service;

import com.testpay.model.payment.PaymentRequest;
import com.testpay.model.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse create(PaymentRequest paymentRequest);
}
