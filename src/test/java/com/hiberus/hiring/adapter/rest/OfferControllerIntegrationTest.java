package com.hiberus.hiring.adapter.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.adapter.persistence.OfferJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.Instant;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OfferControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OfferJpaRepository offerJpaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        offerJpaRepository.deleteAll();
    }

    @Test
    void testCreateAndGetOffer() throws Exception {
        Offer offer = new Offer();
        offer.setBrandId(1);
        offer.setPartNumber("0001002");
        offer.setStartDate(Instant.parse("2020-06-14T00:00:00Z"));
        offer.setEndDate(Instant.parse("2020-06-15T00:00:00Z"));
        offer.setPriority(1);
        offer.setPrice(java.math.BigDecimal.valueOf(35.50));
        offer.setCurr("EUR");

        String json = objectMapper.writeValueAsString(offer);

        // Crear la oferta
        mockMvc.perform(post("/offer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

        // Obtener todas las ofertas
        mockMvc.perform(get("/offer"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

