package com.lld;

public class SmsNotificationFactory implements NotificationFactory {
    public Notifications createNotifications(){
        return new Sms();
    }
}
