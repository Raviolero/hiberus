package com.hiberus.hiring.application.service;

import com.hiberus.hiring.application.port.OfferRepositoryPort;
import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.model.OfferByPartnumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OfferServiceTest {

    private OfferRepositoryPort repository;
    private OfferService offerService;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(OfferRepositoryPort.class);
        offerService = new OfferServiceImpl(repository);
    }

    @Test
    void testCreateOffer() {
        Offer offer = new Offer();
        offer.setBrandId(1);
        offer.setPartNumber("0001002");
        offer.setStartDate(Instant.parse("2020-06-14T00:00:00Z"));
        offer.setEndDate(Instant.parse("2020-06-15T00:00:00Z"));
        offer.setPriority(1);
        offer.setPrice(BigDecimal.valueOf(35.50));

        when(repository.save(offer)).thenReturn(offer);
        Offer result = offerService.createOffer(offer);
        assertNotNull(result);
        assertEquals(1, result.getBrandId());
        verify(repository, times(1)).save(offer);
    }

    @Test
    void testGetOfferByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        Optional<Offer> result = offerService.getOfferById(1L);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetTimetable() {
        // Configurar ofertas de ejemplo
        Offer offer1 = new Offer();
        offer1.setBrandId(1);
        offer1.setPartNumber("0001002");
        offer1.setStartDate(Instant.parse("2020-06-14T00:00:00Z"));
        offer1.setEndDate(Instant.parse("2020-06-15T00:00:00Z"));
        offer1.setPriority(0);
        offer1.setPrice(BigDecimal.valueOf(35.50));

        Offer offer2 = new Offer();
        offer2.setBrandId(1);
        offer2.setPartNumber("0001002");
        offer2.setStartDate(Instant.parse("2020-06-14T15:00:00Z"));
        offer2.setEndDate(Instant.parse("2020-06-14T18:30:00Z"));
        offer2.setPriority(1);
        offer2.setPrice(BigDecimal.valueOf(25.45));

        when(repository.findByBrandIdAndPartNumber(1, "0001002"))
                .thenReturn(List.of(offer1, offer2));
        List<OfferByPartnumber> timetable = offerService.getTimetable(1, "0001002");
        assertNotNull(timetable);
        assertFalse(timetable.isEmpty());
        // Se pueden agregar más aserciones para verificar la correcta división de intervalos.
    }
}
