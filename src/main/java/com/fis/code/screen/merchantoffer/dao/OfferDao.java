package com.fis.code.screen.merchantoffer.dao;

import com.fis.code.screen.merchantoffer.model.Offer;

public interface OfferDao {
    int insertNewOffer(Offer offer);

    boolean cancelOffer(int id);

    Offer getOffer(int id);
}
