package com.notification.emailservice.adapters;

import com.notification.emailservice.core.EmailPayloadDTO;

public interface EmailSenderGateway {
    void sendEmail(EmailPayloadDTO emailPayload);
}
