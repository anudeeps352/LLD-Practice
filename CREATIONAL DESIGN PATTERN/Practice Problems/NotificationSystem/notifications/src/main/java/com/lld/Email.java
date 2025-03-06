package com.lld;

public class Email implements Notifications {
    @Override
    public void notification(){
        System.out.println("This is an email notification");
    }
}
