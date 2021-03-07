package com.fis.code.screen.merchantoffer.service;

import com.fis.code.screen.merchantoffer.BaseTest;
import com.fis.code.screen.merchantoffer.dao.OfferDao;
import com.fis.code.screen.merchantoffer.model.Offer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OfferServiceTest extends BaseTest {

    private OfferServiceImpl offerService;

    @Mock
    private OfferDao offerDao;

    @Test
    void getOffer() {
        offerService = new OfferServiceImpl(offerDao);

        when(offerDao.getOffer(anyInt())).thenReturn(new Offer());

        Offer offer = offerService.getOffer(0);

        assertThat(offer).isEqualTo(new Offer());
    }

    @Test
    void addOffer() {
        offerService = new OfferServiceImpl(offerDao);

        when(offerDao.insertNewOffer(any(Offer.class))).thenReturn(0);

        Integer actual = offerService.addOffer("AddTest", 123, "2021-03-01T00:00:00", true, "Just Testing");

        assertThat(actual).isEqualTo(0);
    }

    @Test
    void cancelOffer() {
        offerService = new OfferServiceImpl(offerDao);
        OfferServiceImpl spy = spy(offerService);

        spy.cancelOffer(1);

        verify(spy, times(1)).cancelOffer(anyInt());

    }
}