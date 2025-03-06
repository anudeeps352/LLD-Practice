package com.lld;


public class Main {
    public static void main(String[] args) {
        NotificationFactory emailFactory = new EmailNotificationFactory();
        NotificationFactory smsFactory = new SmsNotificationFactory();
        Notifications emailNotification = emailFactory.createNotifications();
        Notifications smsNotification = smsFactory.createNotifications();
        emailNotification.notification();
        smsNotification.notification();
    }
}