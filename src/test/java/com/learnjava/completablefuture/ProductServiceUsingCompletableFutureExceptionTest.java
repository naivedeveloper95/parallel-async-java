package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureExceptionTest {
    @Mock
    private ProductInfoService productInfoService;
    @Mock
    private ReviewService reviewService;
    @Mock
    private InventoryService inventoryService;
    @InjectMocks
    ProductServiceUsingCompletableFuture psucf;

    @Test
    void retrieveProductDetailsWithInventory_approach2() {
        String productId = "ABC123";
        when(productInfoService.retrieveProductInfo(any()))
                .thenCallRealMethod();
        when(reviewService.retrieveReviews(any()))
                .thenThrow(new RuntimeException("Exception occurred."));
        when(inventoryService.retrieveInventory(any()))
                .thenCallRealMethod();

        Product product = psucf.retrieveProductDetailsWithInventory_approach2(productId);
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        product.getProductInfo().getProductOptions().forEach(productOption -> {
            assertNotNull(productOption.getInventory());
        });
        assertEquals(0, product.getReview().getNoOfReviews());
    }

    @Test
    void retrieveProductDetailsWithInventoryProductInfoServiceException() {
        String productId = "ABC123";
        when(productInfoService.retrieveProductInfo(any()))
                .thenCallRealMethod();
        when(reviewService.retrieveReviews(any()))
                .thenCallRealMethod();

        Assertions.assertThrows(RuntimeException.class, () -> psucf.retrieveProductDetailsWithInventory_approach2(productId));
    }
}