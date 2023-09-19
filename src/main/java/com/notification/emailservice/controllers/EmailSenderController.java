package com.notification.emailservice.controllers;

import com.notification.emailservice.application.EmailSenderUseCaseImpl;
import com.notification.emailservice.core.EmailPayloadDTO;
import com.notification.emailservice.core.exceptions.EmailServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/email")
public class EmailSenderController {
    private final EmailSenderUseCaseImpl emailSenderService;

    public EmailSenderController(EmailSenderUseCaseImpl emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> sendEmail(@RequestBody EmailPayloadDTO emailPayload) {
        try{
            this.emailSenderService.sendEmail(emailPayload);
            return ResponseEntity.ok("Email sent successfully");
        } catch(EmailServiceException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending email");

        }
    }

}
