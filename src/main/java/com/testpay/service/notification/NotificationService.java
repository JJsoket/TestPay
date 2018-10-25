package com.testpay.service.notification;

import com.testpay.model.notification.NotificationMessage;
import com.testpay.service.notification.signature.INotificationSigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class NotificationService {
    @Autowired
    private NotificationQueue notificationQueue;

    @Autowired
    private INotificationSigner notificationSigner;

    @Value("2")
    private int pollersNumber;

    private ExecutorService executorService;


    public NotificationService() {
    }

    public void register(NotificationMessage notificationMessage, String notificationURL) {
        String notificationSignature = notificationSigner.generateSignature(notificationMessage);
        notificationMessage.setSha2sig(notificationSignature);

        NotificationEvent event = new NotificationEvent(notificationURL,
                notificationMessage,
                NotificationEvent.DEFAULT_RETRIES,
                NotificationEvent.DEFAULT_TIMEOUT_SEC);

        notificationQueue.push(event);
    }

    @PostConstruct
    private void start() {
        this.executorService = Executors.newFixedThreadPool(pollersNumber);
        for (int i = 0; i < pollersNumber; i++) {
            executorService.submit(new NotificationQueuePoller(notificationQueue, 1000));
        }
    }

    @PreDestroy
    private void stop() {
        executorService.shutdownNow();
    }
}
