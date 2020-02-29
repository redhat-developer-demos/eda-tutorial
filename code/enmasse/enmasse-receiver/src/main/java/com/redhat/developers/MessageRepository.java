package com.redhat.developers;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class MessageRepository {

    private Queue<String> queue = new LinkedBlockingQueue<>(11);

    public void add(String message) {
        if (queue.size() > 9) {
            queue.poll();
        }
        queue.offer(message);
    }

    public List<String> getMessages() {
        return new ArrayList<>(queue);
    }

}
