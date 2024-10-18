package com.vass.product_rate_manager.application.usescases;

import com.vass.product_rate_manager.application.exceptions.PriceNotFoundException;
import com.vass.product_rate_manager.domain.models.Price;
import com.vass.product_rate_manager.infrastructure.repositories.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GetFilteredPrice {
    private final PriceRepository priceRepository;

    public GetFilteredPrice(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getPrice(Integer productId, Integer brandId, LocalDateTime applicationDate) {

        return priceRepository.searchHighestPriorityPrice(productId, brandId, applicationDate)
                .orElseThrow(() -> new PriceNotFoundException("No se encontró precio para el producto con ID: " + productId));
    }
}