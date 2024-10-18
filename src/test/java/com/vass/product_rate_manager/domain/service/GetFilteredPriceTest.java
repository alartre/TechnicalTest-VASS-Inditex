package com.vass.product_rate_manager.domain.service;

import com.vass.product_rate_manager.application.exceptions.PriceNotFoundException;
import com.vass.product_rate_manager.domain.models.Price;
import com.vass.product_rate_manager.application.usescases.GetFilteredPrice;
import com.vass.product_rate_manager.infrastructure.repositories.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetFilteredPriceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private GetFilteredPrice getFilteredPrice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPrice_ReturnsPrice_WhenPriceIsFound() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price price = new Price();
        price.setProductId(35455);
        price.setBrandId(1);
        price.setPrice(35.50);

        when(priceRepository.searchHighestPriorityPrice(35455, 1, applicationDate))
                .thenReturn(Optional.of(price));

        Price result = getFilteredPrice.getPrice(35455, 1, applicationDate);

        assertNotNull(result);
        assertEquals(35.50, result.getPrice());
    }

    @Test
    void getPrice_ThrowsPriceNotFoundException_WhenPriceNotFound() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(priceRepository.searchHighestPriorityPrice(35455, 1, applicationDate))
                .thenReturn(Optional.empty());

        PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () -> {
            getFilteredPrice.getPrice(35455, 1, applicationDate);
        });

        assertEquals("No se encontró precio para el producto con ID: 35455", exception.getMessage());
    }
}