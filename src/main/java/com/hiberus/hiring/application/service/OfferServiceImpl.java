package com.hiberus.hiring.application.service;

import com.hiberus.hiring.application.port.OfferRepositoryPort;
import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.model.OfferByPartnumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepositoryPort offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepositoryPort offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public void deleteAllOffers() {
        offerRepository.deleteAll();
    }

    @Override
    public void deleteOfferById(Long offerId) {
        offerRepository.deleteById(offerId);
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public Optional<Offer> getOfferById(Long offerId) {
        return offerRepository.findById(offerId);
    }

    @Override
    public List<OfferByPartnumber> getTimetable(int brandId, String partNumber) {
        List<Offer> offers = offerRepository.findByBrandIdAndPartNumber(brandId, partNumber);
        if (offers.isEmpty()) {
            return Collections.emptyList();
        }

        // Generar puntos críticos (fechas límite) considerando que la tarifa es efectiva hasta 1 segundo antes del endDate.
        SortedSet<Instant> timePoints = new TreeSet<>();
        offers.forEach(offer -> {
            Instant start = offer.getStartDate();
            Instant effectiveEnd = offer.getEndDate().minusSeconds(1);
            timePoints.add(start);
            timePoints.add(effectiveEnd.plusSeconds(1));
        });

        List<OfferByPartnumber> timetable = new ArrayList<>();
        Instant previous = null;
        for (Instant boundary : timePoints) {
            if (previous != null) {
                Instant intervalStart = previous;
                Instant intervalEnd = boundary.minusSeconds(1);
                long seconds = Duration.between(intervalStart, boundary).getSeconds();
                Instant mid = intervalStart.plusSeconds(seconds / 2);

                Offer applicableOffer = null;
                for (Offer offer : offers) {
                    Instant offerStart = offer.getStartDate();
                    Instant offerEffectiveEnd = offer.getEndDate().minusSeconds(1);
                    if (!mid.isBefore(offerStart) && !mid.isAfter(offerEffectiveEnd)) {
                        if (applicableOffer == null || offer.getPriority() > applicableOffer.getPriority()) {
                            applicableOffer = offer;
                        }
                    }
                }
                if (applicableOffer != null) {
                    // Fusionar intervalos contiguos con el mismo precio
                    if (!timetable.isEmpty()) {
                        OfferByPartnumber last = timetable.get(timetable.size() - 1);
                        if (last.price().compareTo(applicableOffer.getPrice()) == 0 &&
                            last.endDate().plusSeconds(1).equals(intervalStart)) {
                            last = new OfferByPartnumber(last.startDate(), intervalEnd, applicableOffer.getPrice());
                            timetable.set(timetable.size() - 1, last);
                        } else {
                            timetable.add(new OfferByPartnumber(intervalStart, intervalEnd, applicableOffer.getPrice()));
                        }
                    } else {
                        timetable.add(new OfferByPartnumber(intervalStart, intervalEnd, applicableOffer.getPrice()));
                    }
                }
            }
            previous = boundary;
        }
        return timetable;
    }
}
