package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {
    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService = new CheckoutService(priceValidatorService);

    @Test
    void checkout() {
        Cart cart = DataSet.createCart(6);
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
        assertTrue(checkoutResponse.getFinalRate() > 0);
    }

    @Test
    void checkoutParallel() {
        Cart cart = DataSet.createCart(6);
        CheckoutResponse checkoutResponse = checkoutService.checkoutParallel(cart);
        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void modifyParallelism() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "100");
        Cart cart = DataSet.createCart(100);
        CheckoutResponse checkoutResponse = checkoutService.checkoutParallel(cart);
        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void numberOfCores() {
        System.out.println("No. of cores :" + Runtime.getRuntime().availableProcessors());
    }

    @Test
    void parallelism() {
        System.out.println("parallelism :" + ForkJoinPool.getCommonPoolParallelism());
    }
}