package com.testpay.notification;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.PriorityQueue;

@Component
public class NotificationQueue {
    private final Object mutex;
    private final PriorityQueue<NotificationEvent> internalQueue;

    public NotificationQueue() {
        this.mutex = new Object();
        this.internalQueue = new PriorityQueue<>(new NotificationEventComparator());
    }

    public NotificationEvent poll() {
        synchronized (mutex) {
            NotificationEvent nextEventToRun = internalQueue.peek();

            if (nextEventToRun != null
                    && nextEventToRun.getNextRunDate().isBefore(LocalDateTime.now())) {
                nextEventToRun = internalQueue.poll();
                nextEventToRun.decreaseRetries();
                return nextEventToRun;
            } else {
                return null;
            }
        }
    }

    public void push(NotificationEvent event) {
        synchronized (mutex) {
            if (event.getNextRunDate() == null) { // for first event push
                event.setNextRunDate(LocalDateTime.now());
                internalQueue.add(event);
            } else if (event.shouldRetry()) {
                LocalDateTime nextRunDate = LocalDateTime.now().plusSeconds(event.getTimeout());
                event.setNextRunDate(nextRunDate);

                internalQueue.add(event);
            }
        }
    }

    public int count() {
        return internalQueue.size();
    }
}
