package com.fis.code.screen.merchantoffer.service;

import com.fis.code.screen.merchantoffer.model.Offer;

public interface OfferService {

    Offer getOffer (int id);

    Integer addOffer(String name, Integer price, String expirationDateString, boolean valid, String description);

    boolean cancelOffer (int id);
}
