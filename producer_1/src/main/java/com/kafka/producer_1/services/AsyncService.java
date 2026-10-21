package com.kafka.producer_1.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncService {
    @Async
    public void testAsync() {
        log.info("thread: {}", Thread.currentThread().getName());
    }

    @Async
    public CompletableFuture<String> loadProduct() {
        String threadName = Thread.currentThread().getName();
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){}
        log.info("loadProduct is running on: {}", threadName);
        return CompletableFuture.completedFuture("thread: " + threadName);
    }
}
