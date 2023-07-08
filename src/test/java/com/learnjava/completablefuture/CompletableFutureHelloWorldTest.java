package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {
    HelloWorldService hws = new HelloWorldService();
    CompletableFutureHelloWorld cfhw = new CompletableFutureHelloWorld(hws);

    @Test
    void helloWorld() {
        CompletableFuture<String> completableFuture = cfhw.helloWorld();

        completableFuture.thenAccept((result) -> {
                    assertEquals("hello world".toUpperCase(), result);
                })
                .join();
    }

    @Test
    void helloWorld_withSize() {
        CompletableFuture<String> completableFuture = cfhw.helloWorld_withSize();
        String test = "hello world";
        completableFuture.thenAccept((result) -> {
                    assertEquals(test.length() + " " + test.toUpperCase(), result);
                })
                .join();
    }

    @Test
    void helloWorldMultipleAsyncCalls() {
        String helloWorld = cfhw.helloWorldMultipleAsyncCalls();
        String test = "hello world!";
        assertEquals(test.toUpperCase(), helloWorld);
    }

    @Test
    void helloWorldThreeAsyncCalls() {
        String helloWorld = cfhw.helloWorldThreeAsyncCalls();
        String test = "hello world! Hi CompletableFuture!";
        assertEquals(test.toUpperCase(), helloWorld);
    }
}