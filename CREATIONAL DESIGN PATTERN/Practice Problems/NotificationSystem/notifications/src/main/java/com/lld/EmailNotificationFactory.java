package com.lld;

public class EmailNotificationFactory implements NotificationFactory {
    public Notifications createNotifications(){
        return new Email();
    }
}
