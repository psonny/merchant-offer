package com.fis.code.screen.merchantoffer.service;

import com.fis.code.screen.merchantoffer.dao.OfferDao;
import com.fis.code.screen.merchantoffer.model.Offer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Service
public class OfferServiceImpl implements OfferService
{
    private OfferDao offerDao;

    public OfferServiceImpl(OfferDao offerDao) {
        this.offerDao = offerDao;
    }

    public Offer getOffer (int id)
    {
        return offerDao.getOffer(id);
    }

    public Integer addOffer(String name, Integer price, String expirationDateString, boolean isCanceled, String description)
    {
        LocalDateTime expirationDate;
        try{
            expirationDate = LocalDateTime.parse(expirationDateString);
        }
        catch(DateTimeParseException e){
            return -1;
        }

        if (price < 0){
            return -1;
        }

        Offer offer = new Offer().builder()
                .name(name)
                .price(price)
                .isCanceled(isCanceled)
                .expireDateTime(expirationDate)
                .description(description)
                .build();

        return offerDao.insertNewOffer(offer);
    }

    public boolean cancelOffer (int id)
    {
        return offerDao.cancelOffer(id);
    }

}
