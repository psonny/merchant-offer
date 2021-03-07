package com.fis.code.screen.merchantoffer.controller;

import com.fis.code.screen.merchantoffer.model.Offer;
import com.fis.code.screen.merchantoffer.service.OfferServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequestMapping("/")
@RestController
public class OfferController
{
    private final OfferServiceImpl offerService;

    public OfferController(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/Offer/{offerId}")
    public ResponseEntity<Offer> getOffer(@PathVariable int offerId)
    {
        Offer offer = offerService.getOffer(offerId);

        ResponseEntity responseEntity;

        if(new Offer().equals(offer)){
            responseEntity = ResponseEntity.badRequest().body("This Offer does not exist!");
        }
        else if (offer.getExpireDateTime().isBefore(LocalDateTime.now())){
            responseEntity = ResponseEntity.badRequest().body("This Offer has expired!");
        }
        else if (offer.isCanceled()){
            responseEntity = ResponseEntity.badRequest().body("This Offer is canceled!");
        }else{
            responseEntity = ResponseEntity.ok(offer);
        }

        return responseEntity;
    }

    @PostMapping(value = "/NewOffer")
    public ResponseEntity<String> addOffer ( String name,  Integer price,  String expirationDate, boolean isCanceled, String description)
    {
        // isCancelled in case they somehow want to create a cancelled offer for whatever reason
        Integer offerId = offerService.addOffer(name, price, expirationDate, isCanceled, description);

        ResponseEntity responseEntity;

        if(offerId < 0){
            responseEntity = ResponseEntity.badRequest().body("Your request is invalid, please make sure all the required data is entered and the date is in yyyy-mm-ddThh:mm:ss (ISO 8601) format");
        }
        else {
            responseEntity = ResponseEntity.ok().body("Your Offer has been created, ID:" + offerId);
        }
        return responseEntity;
    }

    @PutMapping(value = "/CancelOffer/{offerId}")
    public ResponseEntity cancelOffer(@PathVariable Integer offerId)
    {
        boolean valid = offerService.cancelOffer(offerId);
        if(!valid){
            return ResponseEntity.badRequest().body("There aren't any offer to cancel!");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
