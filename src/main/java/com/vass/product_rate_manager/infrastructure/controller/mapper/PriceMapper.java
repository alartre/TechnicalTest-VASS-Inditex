package com.vass.product_rate_manager.infrastructure.controller.mapper;

import com.vass.product_rate_manager.infrastructure.controller.dto.PriceResponseDTO;
import com.vass.product_rate_manager.domain.models.Price;

public class PriceMapper {

    public static PriceResponseDTO toPriceResponseDTO(Price price) {
        PriceResponseDTO priceResponseDTO = new PriceResponseDTO();
        priceResponseDTO.setProductId(price.getProductId());
        priceResponseDTO.setBrandId(price.getBrandId());
        priceResponseDTO.setPriceList(price.getPriceList());
        priceResponseDTO.setStartDate(price.getStartDate());
        priceResponseDTO.setEndDate(price.getEndDate());
        priceResponseDTO.setPrice(price.getPrice());
        return priceResponseDTO;
    }
}
