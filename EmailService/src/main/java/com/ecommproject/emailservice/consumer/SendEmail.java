package com.ecommproject.emailservice.consumer;

import com.ecommproject.emailservice.dto.UserSignupEventDto;
import com.ecommproject.emailservice.service.MailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {
    private final ObjectMapper objectMapper;
    private final MailService mailService;

    public SendEmail(ObjectMapper objectMapper, MailService mailService) {
        this.objectMapper = objectMapper;
        this.mailService = mailService;
    }

    @KafkaListener(topics = "user-signup", groupId = "email-service")
    public void handle(String message) throws JsonProcessingException {
        UserSignupEventDto userSignupEventDto = objectMapper.readValue(message, UserSignupEventDto.class);
        this.mailService.sendEmail(
            userSignupEventDto.getTo(),
            userSignupEventDto.getSubject(),
            userSignupEventDto.getBody()
        );
    }
}
