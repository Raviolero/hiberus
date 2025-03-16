package com.hiberus.hiring.application.service;

import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.model.OfferByPartnumber;
import java.util.List;
import java.util.Optional;

public interface OfferService {
    Offer createOffer(Offer offer);
    void deleteAllOffers();
    void deleteOfferById(Long offerId);
    List<Offer> getAllOffers();
    Optional<Offer> getOfferById(Long offerId);
    List<OfferByPartnumber> getTimetable(int brandId, String partNumber);
}
