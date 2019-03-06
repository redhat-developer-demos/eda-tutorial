package com.redhat.developers;

import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.SingleConnectionFactory;

@SpringBootApplication
public class EnmasseReceiverApplication {

    @Bean
    public AMQPComponent amqps(EnmasseProperties enmasseProperties) {
        AMQPComponent amqpComponent = new AMQPComponent();
        amqpComponent.setConfiguration(new JmsConfiguration(
                new CachingConnectionFactory(new JmsConnectionFactory(enmasseProperties.toJmsRemoteURI()))
        ));
        return amqpComponent;
    }

    public static void main(String[] args) {
        SpringApplication.run(EnmasseReceiverApplication.class, args);
    }

}
