package com.notification.emailservice.application;

import com.notification.emailservice.adapters.EmailSenderGateway;
import com.notification.emailservice.core.EmailPayloadDTO;
import com.notification.emailservice.core.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderUseCaseImpl implements EmailSenderUseCase {

    @Qualifier("sendgrid")
    private final EmailSenderGateway emailSenderGateway;

    public EmailSenderUseCaseImpl(EmailSenderGateway emailSenderGateway) {
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(EmailPayloadDTO emailPayload) {
        emailSenderGateway.sendEmail(emailPayload);
    }
}
