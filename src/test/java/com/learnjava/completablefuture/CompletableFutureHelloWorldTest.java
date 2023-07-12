package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
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

    @Test
    void helloWorldThreeAsyncCallsCustomThreadPool() {
        String helloWorld = cfhw.helloWorldThreeAsyncCallsCustomThreadPool();
        String test = "hello world! Hi CompletableFuture!";
        assertEquals(test.toUpperCase(), helloWorld);
    }

    @Test
    void helloWorld_4_async_calls() {
        String helloWorld = cfhw.helloWorld_4_async_calls();
        String test = "hello world! Hi CompletableFuture! Hey! You good?";
        assertEquals(test.toUpperCase(), helloWorld);
    }

    @Test
    void helloWorld_thenCompose() {
        startTimer();
        CompletableFuture<String> completableFuture = cfhw.helloWorld_thenCompose();

        completableFuture.thenAccept((result) -> {
                    assertEquals("hello world!".toUpperCase(), result);
                })
                .join();
        timeTaken();
    }

    @Test
    void helloWorldThreeAsyncCallsLogAsync() {
        String helloWorld = cfhw.helloWorldThreeAsyncCallsLogAsync();
        String test = "hello world! Hi CompletableFuture!";
        assertEquals(test.toUpperCase(), helloWorld);
    }

    @Test
    void helloWorldThreeAsyncCallsCustomThreadPoolAsync() {
        String helloWorld = cfhw.helloWorldThreeAsyncCallsCustomThreadPoolAsync();
        String test = "hello world! Hi CompletableFuture!";
        assertEquals(test.toUpperCase(), helloWorld);
    }

    @Test
    void anyOf() {
        String helloWorld = cfhw.anyOf();
        assertEquals("hello world!", helloWorld);
    }
}