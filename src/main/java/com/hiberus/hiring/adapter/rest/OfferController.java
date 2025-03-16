package com.hiberus.hiring.adapter.rest;

import com.hiberus.hiring.application.service.OfferService;
import com.hiberus.hiring.domain.model.Offer;
import com.hiberus.hiring.domain.model.OfferByPartnumber;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Offers", description = "Endpoints para la gestión de ofertas")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @Operation(
        summary = "Crear una nueva oferta",
        requestBody = @RequestBody(
            description = "Datos de la oferta",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Offer.class),
                examples = {
                    @ExampleObject(
                        name = "EjemploOferta",
                        value = "{\n  \"brandId\": 1,\n  \"startDate\": \"2020-06-14T00:00:00Z\",\n  \"endDate\": \"2020-06-15T00:00:00Z\",\n  \"priceList\": 1,\n  \"partNumber\": \"0001002\",\n  \"priority\": 1,\n  \"price\": 35.50,\n  \"curr\": \"EUR\"\n}"
                    )
                }
            )
        )
    )
    @PostMapping("/offer")
    public ResponseEntity<Offer> createOffer(@org.springframework.web.bind.annotation.RequestBody Offer offer) {
        Offer createdOffer = offerService.createOffer(offer);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar todas las ofertas")
    @DeleteMapping("/offer")
    public ResponseEntity<Void> deleteAllOffers() {
        offerService.deleteAllOffers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Eliminar una oferta específica por ID")
    @DeleteMapping("/offer/{offerId}")
    public ResponseEntity<Void> deleteOfferById(@PathVariable Long offerId) {
        offerService.deleteOfferById(offerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Obtener todas las ofertas")
    @GetMapping("/offer")
    public ResponseEntity<List<Offer>> getAllOffers() {
        List<Offer> offers = offerService.getAllOffers();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @Operation(summary = "Obtener una oferta específica por ID")
    @GetMapping("/offer/{offerId}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long offerId) {
        Optional<Offer> offer = offerService.getOfferById(offerId);
        return offer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Obtener el timetable de ofertas para un producto")
    @GetMapping("/brand/{brandId}/partnumber/{partnumber}/offer")
    public ResponseEntity<List<OfferByPartnumber>> getTimetable(@PathVariable int brandId, @PathVariable String partnumber) {
        List<OfferByPartnumber> timetable = offerService.getTimetable(brandId, partnumber);
        return new ResponseEntity<>(timetable, HttpStatus.OK);
    }
}
