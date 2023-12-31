package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.domain.ProductInfo;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductServiceUsingCompletableFutureTest {
    private final ProductInfoService productInfoService = new ProductInfoService();
    private final ReviewService reviewService = new ReviewService();
    private final InventoryService inventoryService = new InventoryService();

    ProductServiceUsingCompletableFuture pscf = new ProductServiceUsingCompletableFuture(productInfoService, reviewService, inventoryService);

    @Test
    void retrieveProductDetails() {
        String productId = "ABCD123";
        Product product = pscf.retrieveProductDetails(productId);

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetails_approach2() {
        String productId = "ABCD123";
        CompletableFuture<Product> cfProduct = pscf.retrieveProductDetails_approach2(productId);
        cfProduct.thenAccept((product -> {
            assertNotNull(product);
            assertTrue(product.getProductInfo().getProductOptions().size() > 0);
            assertNotNull(product.getReview());
        }));
    }

    @Test
    void retrieveProductDetailsWithInventory() {
        String productId = "ABCD123";
        Product product = pscf.retrieveProductDetailsWithInventory(productId);
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        product.getProductInfo().getProductOptions().forEach(productOption -> {
            assertNotNull(productOption.getInventory());
        });
    }

    @Test
    void retrieveProductDetailsWithInventory_approach2() {
        String productId = "ABCD123";
        Product product = pscf.retrieveProductDetailsWithInventory_approach2(productId);
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        product.getProductInfo().getProductOptions().forEach(productOption -> {
            assertNotNull(productOption.getInventory());
        });
    }
}