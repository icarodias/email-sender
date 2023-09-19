package com.notification.emailservice.core;

public interface EmailSenderUseCase {
    void send(String to, String subject, String body);
}
