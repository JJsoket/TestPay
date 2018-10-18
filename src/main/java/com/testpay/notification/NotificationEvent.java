package com.testpay.notification;

import com.testpay.model.notification.NotificationMessage;

import java.time.LocalDateTime;

public class NotificationEvent
{
    public static int DEFAULT_TIMEOUT_SEC = 10368;
    public static int DEFAULT_RETRIES = 26;

    private String notificationUrl;
    private NotificationMessage message;

    private int timeout;
    private int retriesLeft;

    private LocalDateTime nextRunDate;

    public NotificationEvent(String notificationUrl,
                             NotificationMessage message,
                             int retriesCount,
                             int timeout)
    {
        this.notificationUrl = notificationUrl;
        this.message = message;
        this.retriesLeft = retriesCount;
        this.timeout = timeout;

        this.nextRunDate = null;
    }

    public int decreaseRetries()
    {
        return --retriesLeft;
    }

    public boolean shouldRetry()
    {
        return retriesLeft > 0;
    }

    public String getNotificationUrl()
    {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl)
    {
        this.notificationUrl = notificationUrl;
    }

    public NotificationMessage getMessage()
    {
        return message;
    }

    public void setMessage(NotificationMessage message)
    {
        this.message = message;
    }

    public int getTimeout()
    {
        return timeout;
    }

    public void setTimeout(int timeout)
    {
        this.timeout = timeout;
    }

    public LocalDateTime getNextRunDate()
    {
        return nextRunDate;
    }

    public void setNextRunDate(LocalDateTime nextRunDate)
    {
        this.nextRunDate = nextRunDate;
    }
}
