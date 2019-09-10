package urstory.kafkaexam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KafkaSendController {

  private final KafkaTemplate kafkaTemplate;

  public KafkaSendController(KafkaTemplate kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @PostMapping("/send")
  public ResponseEntity<String> sendMessage(String message) {
    if(!StringUtils.isEmpty(message)) kafkaTemplate.send("test_topic", "Message is " + message);
    log.info(message);
    return ResponseEntity.ok("");
  }
}