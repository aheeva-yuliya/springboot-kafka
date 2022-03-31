package com.example.kafka.controller;

import com.example.kafka.entity.MessageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private KafkaTemplate<String, String> kafkaTemplate;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void publish(@RequestBody MessageRequest request) {
        System.out.println(request.getMessage());
        kafkaTemplate.send("kafkatopic", request.getMessage());
    }

    @GetMapping
    public void getRequest() {
        MessageRequest request = new MessageRequest("hello");
        System.out.println(request.getMessage());
        kafkaTemplate.send("kafkatopic", request.getMessage());
    }
}
