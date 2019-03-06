package com.redhat.developers.transaction;

import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransactionApplication {

//    @Bean
//    public KafkaComponent kafka() {
//        KafkaComponent kafkaComponent = new KafkaComponent();
//        KafkaConfiguration configuration = new KafkaConfiguration();
//        configuration.setBrokers("my-cluster-kafka-bootstrap.strimzi-infra.svc:9092");
//        kafkaComponent.setConfiguration(configuration);
//        return kafkaComponent;
//    }

    public static void main(String[] args) throws Exception {
        String user = System.getenv().get("WORKSHOP_USER");
        if (user == null || !user.matches("user\\d{1,3}")) {
            throw new IllegalStateException("You have to provide the WORKSHOP_USER environment variable with the format 'user\\d{1,3}'");
        }
        SpringApplication.run(TransactionApplication.class, args);
    }

}
