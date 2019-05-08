package com.redhat.developers.eda;

import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaConfiguration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@ApplicationScoped
public class KafkaReceiverApplication {

    @Produces
    @Named("kafka")
    public KafkaComponent kafka() {
        KafkaComponent kafkaComponent = new KafkaComponent();
        KafkaConfiguration configuration = new KafkaConfiguration();
        configuration.setBrokers("localhost:9092");
        kafkaComponent.setConfiguration(configuration);
        return kafkaComponent;
    }

}
