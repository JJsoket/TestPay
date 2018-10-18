package com.testpay.notification;

class NotificationQueuePoller implements Runnable
{
    private NotificationQueue notificationQueue;
    private NotificationSubmitter notificationSubmitter;

    private int pollTimeout;

    NotificationQueuePoller(NotificationQueue notificationQueue, int pollTimeout)
    {
        this.notificationQueue = notificationQueue;
        this.pollTimeout = pollTimeout;

        this.notificationSubmitter = new NotificationSubmitter();
    }

    @Override
    public void run()
    {
        while(!Thread.currentThread().isInterrupted())
        {
            try{
                NotificationEvent notificationEvent = notificationQueue.poll();

                if (notificationEvent != null)
                {
                    try
                    {
                        boolean submissionStatus = notificationSubmitter.submit(notificationEvent);

                        if (!submissionStatus)
                        {
                            notificationQueue.push(notificationEvent);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                Thread.sleep(pollTimeout);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
