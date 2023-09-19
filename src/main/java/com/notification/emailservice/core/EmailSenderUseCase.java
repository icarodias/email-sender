package com.notification.emailservice.core;

public interface EmailSenderUseCase {
    void sendEmail(EmailPayloadDTO emailPayload);
}
