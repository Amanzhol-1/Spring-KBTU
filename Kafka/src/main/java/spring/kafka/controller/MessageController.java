package spring.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.kafka.model.Farewell;
import spring.kafka.model.Greeting;
import spring.kafka.service.KafkaMessageProducer;

@RestController
@RequestMapping("/kafka")
public class MessageController {

    @Autowired
    private KafkaMessageProducer kafkaMessageProducer;

    @PostMapping("/sendGreeting")
    public String sendGreetingMessage(@RequestBody Greeting greeting) {
        kafkaMessageProducer.sendGreeting(greeting);
        return "Greeting message sent: " + greeting;
    }

    @PostMapping("/sendFarewell")
    public String sendFarewellMessage(@RequestBody Farewell farewell) {
        kafkaMessageProducer.sendFarewell(farewell);
        return "Farewell message sent: " + farewell;
    }
}
