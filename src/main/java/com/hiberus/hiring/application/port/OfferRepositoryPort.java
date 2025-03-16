package com.hiberus.hiring.application.port;

import com.hiberus.hiring.domain.model.Offer;
import java.util.List;
import java.util.Optional;

public interface OfferRepositoryPort {
    Offer save(Offer offer);
    void deleteAll();
    void deleteById(Long offerId);
    List<Offer> findAll();
    Optional<Offer> findById(Long offerId);
    List<Offer> findByBrandIdAndPartNumber(int brandId, String partNumber);
}
