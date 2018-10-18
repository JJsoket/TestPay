package com.testpay.notification.signature;

import com.testpay.model.notification.NotificationMessage;

public interface INotificationSigner {
    String generateSignature(NotificationMessage message);
}
