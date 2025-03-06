package com.lld;

public class PushNotificationFactory implements NotificationFactory {
     public Notifications createNotifications(){
        return new Push();
    }
}
