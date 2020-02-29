package com.redhat.developers.eda;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class MessageSender {

    private int count = 1;

    @Outgoing("my-topic")
    public Flowable<String> generate() {
        return Flowable.interval(1, TimeUnit.SECONDS)
                .map(tick -> Integer.toString(count++));
    }

}