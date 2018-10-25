package com.testpay.service.notification;

import java.util.Comparator;

class NotificationEventComparator implements Comparator<NotificationEvent> {
    @Override
    public int compare(NotificationEvent o1, NotificationEvent o2) {
        if (o1 == null || o2 == null) {
            throw new NullPointerException();
        }

        if (o1.getNextRunDate() == null && o2.getNextRunDate() == null) {
            return 0;
        }

        if (o1.getNextRunDate() == null) {
            return -1;
        }

        if (o2.getNextRunDate() == null) {
            return 1;
        }

        return o1.getNextRunDate().compareTo(o2.getNextRunDate());
    }
}
