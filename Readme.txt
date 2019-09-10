idocker-compose.yml 파일

 version: '2'

 networks:
   test:

 services:
   zookeeper:
     image: wurstmeister/zookeeper:3.4.6
     container_name: zookeeper
     ports:
       - "2181:2181"
     networks:
       - test

   kafka:
     image: wurstmeister/kafka:2.12-2.0.1
     container_name: kafka
     environment:
       KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
       KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092
       KAFKA_ADVERTISED_HOST_NAME: 127.0.0.1
       KAFKA_ADVERTISED_PORT: 9092
       KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
     volumes:
       - /var/run/docker.sock:/var/run/docker.sock
     ports:
       - "9092:9092"
     depends_on:
       - zookeeper
     networks:
       - test

 ----------------
토픽생성 및 확인

 ./kafka-topics.sh -create -topic test_topic --zookeeper localhost:2181 --partitions 1 --replication-factor 1

 ./kafka-topics.sh --zookeeper localhost:2181 --list

 ./kafka-console-producer.sh --broker-list localhost:9092 --topic test_topic

 ./kafka-console-consumer.sh --topic test_topic --bootstrap-server localhost:9092 --from-beginning
