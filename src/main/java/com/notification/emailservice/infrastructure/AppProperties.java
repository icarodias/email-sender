package com.notification.emailservice.infrastructure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private Mail mail;

    private SendGrid sendGrid;

    @Data
    public static class Mail {
        private String from;
    }

    @Data
    public static class SendGrid {
        private String apiKey;
    }

}
