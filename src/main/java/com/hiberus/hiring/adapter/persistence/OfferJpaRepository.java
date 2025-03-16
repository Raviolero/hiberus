package com.hiberus.hiring.adapter.persistence;

import com.hiberus.hiring.domain.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OfferJpaRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByBrandIdAndPartNumber(int brandId, String partNumber);
}

