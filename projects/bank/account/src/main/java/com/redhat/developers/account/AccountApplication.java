package com.redhat.developers.account;

import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaConfiguration;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.CachingConnectionFactory;

@SpringBootApplication
public class AccountApplication {

    @Bean
    public AMQPComponent amqps(EnmasseProperties enmasseProperties) {
        AMQPComponent amqpComponent = new AMQPComponent();
        amqpComponent.setConfiguration(new JmsConfiguration(
                new CachingConnectionFactory(new JmsConnectionFactory(enmasseProperties.toJmsRemoteURI()))
        ));
        return amqpComponent;
    }

    @Bean
    public KafkaComponent kafka() {
        KafkaComponent kafkaComponent = new KafkaComponent();
        KafkaConfiguration configuration = new KafkaConfiguration();
        configuration.setBrokers("my-cluster-kafka-bootstrap.strimzi-infra.svc:9092");
        configuration.setGroupId("account");
        kafkaComponent.setConfiguration(configuration);
        return kafkaComponent;
    }

    public static void main(String[] args) {
        String user = System.getenv().get("WORKSHOP_USER");
        if (user == null || !user.matches("user\\d{1,3}")) {
            throw new IllegalStateException("You have to provide the WORKSHOP_USER environment variable with the format 'user\\d{1,3}'");
        }
        SpringApplication.run(AccountApplication.class, args);
    }

}
