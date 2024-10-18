package com.vass.product_rate_manager.application.controller;

import com.vass.product_rate_manager.application.exceptions.PriceNotFoundException;
import com.vass.product_rate_manager.domain.models.Price;
import com.vass.product_rate_manager.application.usescases.GetFilteredPrice;
import com.vass.product_rate_manager.infrastructure.controller.GetFilteredPriceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GetFilteredPriceController.class)
class GetFilteredPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetFilteredPrice getFilteredPrice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test1_priceAt10amOn14th() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price price = new Price();
        price.setProductId(35455);
        price.setBrandId(1);
        price.setPrice(35.50);
        price.setStartDate(applicationDate);
        price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));

        when(getFilteredPrice.getPrice(35455, 1, applicationDate)).thenReturn(price);

        mockMvc.perform(get("/products/35455/prices")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14 10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void test2_priceAt4pmOn14th() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);
        Price price = new Price();
        price.setProductId(35455);
        price.setBrandId(1);
        price.setPrice(25.45);
        price.setStartDate(applicationDate);
        price.setEndDate(LocalDateTime.of(2020, 6, 14, 18, 30));

        when(getFilteredPrice.getPrice(35455, 1, applicationDate)).thenReturn(price);

        mockMvc.perform(get("/products/35455/prices")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14 16:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45));
    }
    @Test
    void test3_priceAt9pmOn14th() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0);

        when(getFilteredPrice.getPrice(35455, 1, applicationDate))
                .thenThrow(new PriceNotFoundException("No se encontró precio para el producto con ID: 35455"));

        mockMvc.perform(get("/products/35455/prices")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14 21:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No se encontró precio para el producto con ID: 35455"));
    }

    @Test
    void test4_priceAt10amOn15th() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);
        Price price = new Price();
        price.setProductId(35455);
        price.setBrandId(1);
        price.setPrice(30.50);

        when(getFilteredPrice.getPrice(35455, 1, applicationDate)).thenReturn(price);

        mockMvc.perform(get("/products/35455/prices")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-15 10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    void test5_priceAt9pmOn16th() throws Exception {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0);
        Price price = new Price();
        price.setProductId(35455);
        price.setBrandId(1);
        price.setPrice(38.95);

        when(getFilteredPrice.getPrice(35455, 1, applicationDate)).thenReturn(price);

        mockMvc.perform(get("/products/35455/prices")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-16 21:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95));
    }
}