package micro.library.kafka.service;

import lombok.extern.slf4j.Slf4j;
import micro.library.kafka.domain.KafkaEvent;
import micro.library.kafka.stream.EventKafkaStream;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
@Slf4j
public class KafkaServiceImpl implements KafkaService {

    private final EventKafkaStream eventKafkaStream;

    public KafkaServiceImpl(EventKafkaStream eventKafkaStream) {
        this.eventKafkaStream = eventKafkaStream;
    }

    @Override
    public void sendEvent(KafkaEvent kafkaEvent) {
        log.info("sending event {}", kafkaEvent);

        MessageChannel messageChannel = eventKafkaStream.output();
        messageChannel.send(MessageBuilder.withPayload(kafkaEvent)
                        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                        .build());
    }

}
