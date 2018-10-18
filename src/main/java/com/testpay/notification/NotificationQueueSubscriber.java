package com.testpay.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class NotificationQueueSubscriber {
    private NotificationQueue notificationQueue;
    private int pollersNumber;

    private List<NotificationQueuePoller> pollers;
    private ExecutorService executorService;

    public NotificationQueueSubscriber(@Autowired NotificationQueue notificationQueue,
                                       @Value("2") int pollersNumber) {
        this.notificationQueue = notificationQueue;
        this.pollersNumber = pollersNumber;

        this.executorService = Executors.newFixedThreadPool(pollersNumber);

        this.pollers = new ArrayList<>(pollersNumber);
        for (int i = 0; i < pollersNumber; i++) {
            this.pollers.add(new NotificationQueuePoller(notificationQueue, 1000));
        }
    }

    @PostConstruct
    public void start() {
        for (NotificationQueuePoller poller : pollers) {
            executorService.submit(poller);
        }
    }

    @PreDestroy
    public void stop() {
        executorService.shutdownNow();
    }
}
