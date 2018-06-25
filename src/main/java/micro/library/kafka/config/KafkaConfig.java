package micro.library.kafka.config;

import micro.library.kafka.stream.EventKafkaStream;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(EventKafkaStream.class)
public class KafkaConfig {

}
