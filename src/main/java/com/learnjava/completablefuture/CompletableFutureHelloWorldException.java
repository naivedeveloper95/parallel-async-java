package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorldException {
    private final HelloWorldService helloWorldService;

    public CompletableFutureHelloWorldException(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public String helloWorldThreeAsyncCallsHandle() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String helloWorld = hello
                .handle((res, ex) -> {
                    if (ex != null) {
                        log("Exception for hello, Exception is :: " + ex.getMessage());
                        return "";
                    }
                    return res;
                })
                .thenCombine(world, (h, w) -> h + w)
                .handle((res, ex) -> {
                    if (ex != null) {
                        log("Exception for world, Exception is :: " + ex.getMessage());
                        return "";
                    }
                    return res;
                })
                .thenCombine(completableFuture, (prev, cur) -> prev + cur)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return helloWorld;
    }

    public String helloWorldThreeAsyncCallsExceptionally() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String helloWorld = hello
                .exceptionally(ex -> {
                    log("Exception for hello, Exception is :: " + ex.getMessage());
                    return "";
                })
                .thenCombine(world, (h, w) -> h + w)
                .exceptionally(ex -> {
                    log("Exception for world, Exception is :: " + ex.getMessage());
                    return "";
                })
                .thenCombine(completableFuture, (prev, cur) -> prev + cur)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return helloWorld;
    }

    public String helloWorldThreeAsyncCallsWhenComplete() {
        startTimer();
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String helloWorld = hello
                .whenComplete((res, ex) -> {
                    if (ex != null) {
                        log("Exception for hello, Exception is :: " + ex.getMessage());
                    }
                })
                .thenCombine(world, (h, w) -> h + w)
                .whenComplete((res, ex) -> {
                    if (ex != null) {
                        log("Exception for world, Exception is :: " + ex.getMessage());
                    }
                })
                .exceptionally(e -> {
                    log("Exception after the first combine :: " + e.getMessage());
                    return "";
                })
                .thenCombine(completableFuture, (prev, cur) -> prev + cur)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();
        return helloWorld;
    }

}
