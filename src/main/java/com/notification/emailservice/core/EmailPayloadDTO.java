package com.notification.emailservice.core;

import lombok.Data;

@Data
public class EmailPayloadDTO {

    private String to;

    private String subject;

    private String body;
}
