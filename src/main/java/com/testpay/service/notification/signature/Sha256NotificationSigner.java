package com.testpay.service.notification.signature;

import com.testpay.model.notification.NotificationMessage;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class Sha256NotificationSigner implements INotificationSigner {
    @Override
    public String generateSignature(NotificationMessage message) {
        try {
            return DigestUtils.sha256Hex(getStringToSign(message));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getStringToSign(NotificationMessage message) {
        return new StringBuilder().append(message.getCurrency())
                .append(message.getAmount())
                .append(message.getId())
                .append(message.getExternalId())
                .append(message.getStatus())
                .toString();
    }
}
