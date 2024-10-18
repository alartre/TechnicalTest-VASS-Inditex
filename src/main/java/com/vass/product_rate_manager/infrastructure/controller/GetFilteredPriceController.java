package com.vass.product_rate_manager.infrastructure.controller;


import com.vass.product_rate_manager.infrastructure.controller.dto.PriceResponseDTO;
import com.vass.product_rate_manager.infrastructure.controller.mapper.PriceMapper;
import com.vass.product_rate_manager.domain.models.Price;
import com.vass.product_rate_manager.application.usescases.GetFilteredPrice;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@Validated
public class GetFilteredPriceController {

    private final GetFilteredPrice getFilteredPrice;

    //products/{product-id}/prices
    @GetMapping("/products/{productId}/prices")
    public ResponseEntity<PriceResponseDTO> getPrice(
            @PathVariable("productId") @NotNull Integer productId,
            @RequestParam("brandId") @NotNull Integer brandId,
            @RequestParam("applicationDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate) {

        Price price = getFilteredPrice.getPrice(productId, brandId, applicationDate);
        PriceResponseDTO dto = PriceMapper.toPriceResponseDTO(price);

        return ResponseEntity.ok(dto);
    }
}