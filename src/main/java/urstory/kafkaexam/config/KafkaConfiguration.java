package urstory.kafkaexam.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 카프카 설정
 *
 * @author wedul
 * @since 2019-01-24
 **/
@Configuration
@EnableKafka
@PropertySource("classpath:kafka.properties")
public class KafkaConfiguration {

  @Autowired
  private Environment env;

  private Map<String, Object> producerConfig() {
    Map<String, Object> config = new HashMap<>();

    config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("bootstrap.servers"));
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    return config;
  }

  @Bean
  public KafkaTemplate<String, String > kafkaTemplate() {
    return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfig()));
  }

}

