package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {

    private final HelloWorldService helloWorldService;
    private String cur;

    public CompletableFutureHelloWorld(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture.supplyAsync(helloWorldService::helloWorld)
                .thenApply(String::toUpperCase);
    }

    public CompletableFuture<String> helloWorld_withSize() {
        return CompletableFuture.supplyAsync(helloWorldService::helloWorld)
                .thenApply((result) -> result.length() + " " + result.toUpperCase());
    }

    public String helloWorld_approach() {
        String hello = helloWorldService.hello();
        String world = helloWorldService.world();

        return hello + world;
    }

    public String helloWorldMultipleAsyncCalls() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);

        String helloWorld = hello.thenCombine(world, (h, w) -> h + w)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return helloWorld;
    }

    public String helloWorldThreeAsyncCalls() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String helloWorld = hello
                .thenCombine(world, (h, w) -> h + w)
                .thenCombine(completableFuture, (prev, cur) -> prev + cur)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return helloWorld;
    }

    public String helloWorld_4_async_calls() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });
        CompletableFuture<String> compFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hey! You good?";
        });

        String helloWorld = hello
                .thenCombine(world, (h, w) -> h + w)
                .thenCombine(completableFuture, (prev, cur) -> prev + cur)
                .thenCombine(compFuture, (prev, cur) -> prev + cur)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return helloWorld;
    }

    public static void main(String[] args) {

        HelloWorldService helloWorldService = new HelloWorldService();

        CompletableFuture.supplyAsync(helloWorldService::helloWorld)
                .thenApply(String::toUpperCase)
                .thenAccept((result) -> {
                    log("Result is : " + result);
                })
                .join();
        log("Done!");
//        delay(2000);
    }
}
