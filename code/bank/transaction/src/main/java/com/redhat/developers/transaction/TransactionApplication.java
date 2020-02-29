package com.redhat.developers.transaction;

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
public class TransactionApplication {

    @Bean
    public AMQPComponent amqps(EnmasseProperties enmasseProperties) {
        AMQPComponent amqpComponent = new AMQPComponent();
        amqpComponent.setConfiguration(new JmsConfiguration(
                new CachingConnectionFactory(new JmsConnectionFactory(enmasseProperties.toJmsRemoteURI()))
        ));
        return amqpComponent;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TransactionApplication.class, args);
    }

}
