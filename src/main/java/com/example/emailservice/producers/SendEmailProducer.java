package com.example.emailservice.producers;

import com.example.emailservice.dtos.SendEmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendEmailProducer {

    // No need to write bean config for this as a bean gets auto-created
    // based on the kafka maven dependency
    private KafkaTemplate<String, String> kafkaTemplate;
    ObjectMapper objectMapper;

    public SendEmailProducer(KafkaTemplate<String, String> kafkaTemplate,
                             ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public String sendEmail(SendEmailDto emailDto) throws JsonProcessingException {
        kafkaTemplate.send("sendEmail", objectMapper.writeValueAsString(emailDto));
        return "Email sent";
    }
}
