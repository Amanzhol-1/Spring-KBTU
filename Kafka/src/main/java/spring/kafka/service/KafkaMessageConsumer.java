package spring.kafka.service;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;
import spring.kafka.model.Farewell;
import spring.kafka.model.Greeting;


@Service
public class KafkaMessageConsumer {

    @KafkaListener(topics = "baeldung", groupId = "foo")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }

    @KafkaListener(topics = {"baeldung", "topic2"}, groupId = "foo")
    public void listenMultipleTopics(String message) {
        System.out.println("Received Message from multiple topics: " + message);
    }

    @KafkaListener(topics = "baeldung")
    public void listenWithHeaders(@Payload String message,
                                  @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        System.out.println("Received Message: " + message + " from partition: " + partition);
    }


//    @KafkaListener(
//            topicPartitions = @TopicPartition(topic = "baeldung", partitionOffsets = {
//                    @PartitionOffset(partition = "0", initialOffset = "0"),
//                    @PartitionOffset(partition = "3", initialOffset = "0")
//            }),
//            containerFactory = "partitionsKafkaListenerContainerFactory"
//    )
//    public void listenToPartition(@Payload String message,
//                                  @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
//        System.out.println("Received Message: " + message + " from partition: " + partition);
//    }


    @KafkaListener(topicPartitions = @TopicPartition(topic = "baeldung", partitions = { "0", "1" }))
    public void listenToSpecificPartitions(String message) {
        System.out.println("Received Message from specific partitions: " + message);
    }

//    @KafkaListener(
//            topics = "baeldung",
//            containerFactory = "filterKafkaListenerContainerFactory")
//    public void listenWithFilter(String message) {
//        System.out.println("Received Message in filtered listener: " + message);
//    }




    @KafkaListener(
            topics = "baeldung",
            containerFactory = "multiTypeKafkaListenerContainerFactory"
    )
    public void greetingListener(Greeting greeting) {
        System.out.println("Received Greeting message: " + greeting.getMsg() + " from " + greeting.getName());
    }

    @KafkaListener(
            topics = "baeldung",
            containerFactory = "multiTypeKafkaListenerContainerFactory"
    )
    public void farewellListener(Farewell farewell) {
        System.out.println("Received Farewell message: " + farewell.getMessage() + " with remaining minutes: " + farewell.getRemainingMinutes());
    }

    @KafkaListener(
            topics = "baeldung",
            containerFactory = "multiTypeKafkaListenerContainerFactory"
    )
    public void listenMultiTypeMessages(Object message) {
        if (message instanceof Greeting) {
            Greeting greeting = (Greeting) message;
            System.out.println("Received Greeting: " + greeting.getMsg() + ", " + greeting.getName());
        } else if (message instanceof Farewell) {
            Farewell farewell = (Farewell) message;
            System.out.println("Received Farewell: " + farewell.getMessage() + " with remaining minutes: " + farewell.getRemainingMinutes());
        }
    }
}
