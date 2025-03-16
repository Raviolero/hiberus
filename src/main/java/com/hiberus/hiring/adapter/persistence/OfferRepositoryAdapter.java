package com.hiberus.hiring.adapter.persistence;

import com.hiberus.hiring.application.port.OfferRepositoryPort;
import com.hiberus.hiring.domain.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class OfferRepositoryAdapter implements OfferRepositoryPort {

    private final OfferJpaRepository jpaRepository;

    @Autowired
    public OfferRepositoryAdapter(OfferJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Offer save(Offer offer) {
        return jpaRepository.save(offer);
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }

    @Override
    public void deleteById(Long offerId) {
        jpaRepository.deleteById(offerId);
    }

    @Override
    public List<Offer> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Offer> findById(Long offerId) {
        return jpaRepository.findById(offerId);
    }

    @Override
    public List<Offer> findByBrandIdAndPartNumber(int brandId, String partNumber) {
        return jpaRepository.findByBrandIdAndPartNumber(brandId, partNumber);
    }
}
