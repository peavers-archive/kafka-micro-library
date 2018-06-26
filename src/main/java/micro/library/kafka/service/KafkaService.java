package micro.library.kafka.service;

import micro.library.kafka.domain.KafkaEvent;

public interface KafkaService {
    void sendEvent(KafkaEvent kafkaEvent);
}
