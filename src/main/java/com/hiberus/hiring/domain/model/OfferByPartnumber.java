package com.hiberus.hiring.domain.model;

import java.math.BigDecimal;
import java.time.Instant;

public record OfferByPartnumber(Instant startDate, Instant endDate, BigDecimal price) { }

