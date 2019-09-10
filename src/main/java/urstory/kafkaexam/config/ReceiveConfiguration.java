package urstory.kafkaexam.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * study
 *
 * @author wedul
 * @since 2019-01-25
 **/
@Slf4j
@Component
public class ReceiveConfiguration {

  @KafkaListener(topics = "test_topic", groupId = "console-consumer-1970")
  public void receive(String payload) {
    log.info("received payload='{}'", payload);
  }

}