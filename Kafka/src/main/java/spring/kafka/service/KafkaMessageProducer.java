package spring.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import spring.kafka.model.Farewell;
import spring.kafka.model.Greeting;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessageProducer {

    private final KafkaTemplate<String, Object> multiTypeKafkaTemplate;

    @Autowired
    public KafkaMessageProducer(@Qualifier("multiTypeKafkaTemplate") KafkaTemplate<String, Object> multiTypeKafkaTemplate) {
        this.multiTypeKafkaTemplate = multiTypeKafkaTemplate;
    }

    private final String topicName = "baeldung";

    public void sendGreeting(Greeting greeting) {
        multiTypeKafkaTemplate.send(topicName, greeting);
    }

    public void sendFarewell(Farewell farewell) {
        multiTypeKafkaTemplate.send(topicName, farewell);
    }

    public void sendMessage(String msg) {
        multiTypeKafkaTemplate.send(topicName, msg);
    }
}

