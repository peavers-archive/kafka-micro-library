package micro.library.kafka.service;

import micro.library.kafka.domain.KafkaEvent;
import micro.library.kafka.stream.EventKafkaStream;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
@EnableConfigurationProperties(EventKafkaStream.class)
public class KafkaServiceImpl implements KafkaService {

    private final EventKafkaStream eventKafkaStream;

    public KafkaServiceImpl(EventKafkaStream stream) {
        this.eventKafkaStream = stream;
    }

    @Override
    public void sendEvent(KafkaEvent kafkaEvent) {
        MessageChannel messageChannel = eventKafkaStream.output();
        messageChannel.send(MessageBuilder.withPayload(kafkaEvent)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}