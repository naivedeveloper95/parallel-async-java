package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompletableFutureHelloWorldExceptionTest {
    @Mock
    HelloWorldService helloWorldService = mock(HelloWorldService.class);
    @InjectMocks
    CompletableFutureHelloWorldException hwcfe;

    @Test
    void helloWorldThreeAsyncCallsHandle() {
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred."));
        when(helloWorldService.world()).thenCallRealMethod();

        String result = hwcfe.helloWorldThreeAsyncCallsHandle();

        assertEquals(" world! Hi CompletableFuture!".toUpperCase(), result);
    }

    @Test
    void helloWorldThreeAsyncCallsHandle_2() {
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred."));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred."));

        String result = hwcfe.helloWorldThreeAsyncCallsHandle();
        assertEquals(" Hi CompletableFuture!".toUpperCase(), result);
    }

    @Test
    void helloWorldThreeAsyncCallsHandle_3() {
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        String result = hwcfe.helloWorldThreeAsyncCallsHandle();
        assertEquals("hello world! Hi CompletableFuture!".toUpperCase(), result);
    }

    @Test
    void helloWorldThreeAsyncCallsExceptionally() {
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        String result = hwcfe.helloWorldThreeAsyncCallsExceptionally();
        assertEquals("hello world! Hi CompletableFuture!".toUpperCase(), result);
    }

    @Test
    void helloWorldThreeAsyncCallsExceptionally_2() {
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred."));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred."));

        String result = hwcfe.helloWorldThreeAsyncCallsHandle();
        assertEquals(" Hi CompletableFuture!".toUpperCase(), result);
    }

    @Test
    void helloWorldThreeAsyncCallsWhenComplete() {
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        String result = hwcfe.helloWorldThreeAsyncCallsWhenComplete();
        assertEquals("hello world! Hi CompletableFuture!".toUpperCase(), result);
    }

    @Test
    void helloWorldThreeAsyncCallsWhenComplete_2() {
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred."));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred."));

        String result = hwcfe.helloWorldThreeAsyncCallsWhenComplete();
        assertEquals(" Hi CompletableFuture!".toUpperCase(), result);
    }
}