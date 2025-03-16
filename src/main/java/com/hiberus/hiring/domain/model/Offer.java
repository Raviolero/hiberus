package com.hiberus.hiring.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID de la oferta, se genera automáticamente", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long offerId;
    
    @Schema(example = "1", description = "ID de la marca")
    private int brandId;
    
    @Schema(example = "2020-06-14T00:00:00Z", description = "Fecha de inicio de la oferta en formato ISO8601")
    private Instant startDate;
    
    @Schema(example = "2020-06-15T00:00:00Z", description = "Fecha de fin de la oferta en formato ISO8601")
    private Instant endDate;
    
    @Schema(example = "1", description = "Identificador de la lista de precios")
    private int priceList;
    
    @Schema(example = "0001002", description = "Código del producto")
    private String partNumber;
    
    @Schema(example = "1", description = "Prioridad para resolver solapamientos")
    private int priority;
    
    @Schema(example = "35.50", description = "Precio final de la oferta")
    private BigDecimal price;
    
    @Schema(example = "EUR", description = "Código de moneda ISO")
    private String curr;

    public Offer() {
    }

    // Getters y Setters
    public Long getOfferId() {
        return offerId;
    }
    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }
    public int getBrandId() {
        return brandId;
    }
    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }
    public Instant getStartDate() {
        return startDate;
    }
    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }
    public Instant getEndDate() {
        return endDate;
    }
    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }
    public int getPriceList() {
        return priceList;
    }
    public void setPriceList(int priceList) {
        this.priceList = priceList;
    }
    public String getPartNumber() {
        return partNumber;
    }
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getCurr() {
        return curr;
    }
    public void setCurr(String curr) {
        this.curr = curr;
    }
}
