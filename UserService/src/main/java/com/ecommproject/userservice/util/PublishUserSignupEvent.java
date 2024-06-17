package com.ecommproject.userservice.util;

import com.ecommproject.userservice.dto.UserSignupEventDto;
import com.ecommproject.userservice.model.User;
import com.ecommproject.userservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublishUserSignupEvent {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LogManager.getLogger(UserService.class);

    public PublishUserSignupEvent(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(User user) {
        UserSignupEventDto userSignupEventDto = new UserSignupEventDto();
        userSignupEventDto.setTo(user.getEmail());
        userSignupEventDto.setSubject("Welcome to our platform");
        String bodyMessage = String.format("Hello %s, Thank you for signing up with us. We are excited to have you on board.", user.getName());
        userSignupEventDto.setBody(bodyMessage);
        userSignupEventDto.setEvent("user-signup");

        try {
            String userSignupEventDtoJson = objectMapper.writeValueAsString(userSignupEventDto);
            logger.info("Sending UserSignupEventDto to Kafka: " + userSignupEventDtoJson);
            this.kafkaTemplate.send("user-signup", userSignupEventDtoJson);
        } catch (JsonProcessingException e) {
            logger.error("Error processing JSON", e);
        }
    }
}
