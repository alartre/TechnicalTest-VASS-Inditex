package com.vass.product_rate_manager.infrastructure.repositories;

import com.vass.product_rate_manager.domain.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query("SELECT p FROM Price p WHERE p.productId = :productId AND p.brandId = :brandId AND :date BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC LIMIT 1")
    Optional<Price> searchHighestPriorityPrice(
            @Param("productId") int productId,
            @Param("brandId") int brandId,
            @Param("date") LocalDateTime date
    );
}