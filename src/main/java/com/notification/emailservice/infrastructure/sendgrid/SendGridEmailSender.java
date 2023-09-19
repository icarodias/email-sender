package com.notification.emailservice.infrastructure.sendgrid;

import com.notification.emailservice.adapters.EmailSenderGateway;
import com.notification.emailservice.core.EmailPayloadDTO;
import com.notification.emailservice.core.exceptions.EmailServiceException;
import com.notification.emailservice.infrastructure.AppProperties;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service("sendgrid")
public class SendGridEmailSender implements EmailSenderGateway {
    private final AppProperties properties;
    private final String emailSender;

    public SendGridEmailSender(AppProperties properties) {
        this.properties = properties;
        this.emailSender = properties.getMail().getFrom();
    }

    @Override
    public void sendEmail(EmailPayloadDTO payload) {
        final Email from = new Email(emailSender);
        final Email to = new Email(payload.getTo());
        final String subject = payload.getSubject();
        final Content body = new Content("text",payload.getBody());


        Mail mail = new Mail(from, subject, to, body);

        SendGrid sg = new SendGrid(properties.getSendGrid().getApiKey());
        Request request = new Request();
        Response response = null;

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = sg.api(request);

            if (HttpStatus.valueOf(response.getStatusCode()).isError()) {
                throw new Exception(response.getBody());
            }

            log.info("[Sendgrid] StatusCode={}", response.getStatusCode());
        } catch (Exception ex) {
            log.error("[Sendgrid] StatusCode={}", response.getStatusCode());
            log.error("[Sendgrid] Body={}", response.getBody());
            log.error("[Sendgrid] Headers={}", response.getHeaders());
            throw new EmailServiceException("Failure while sending email");
        }
    }
}
