package com.fis.code.screen.merchantoffer.controller;

import com.fis.code.screen.merchantoffer.BaseTest;
import com.fis.code.screen.merchantoffer.model.Offer;
import com.fis.code.screen.merchantoffer.service.OfferServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class OfferControllerTest extends BaseTest {

    private OfferController offerController;

    @Mock
    private OfferServiceImpl offerService;

    @Test
    void getOffer()
    {
        offerController = new OfferController(offerService);

        when(offerService.getOffer(anyInt())).thenReturn(new Offer().builder()
                .description("I am Get test")
                .name("GetTest")
                .price(123)
                .isCanceled(false)
                .expireDateTime(LocalDateTime.of(2022,
                        Month.MARCH, 1, 0, 0, 0))
                .build());

        ResponseEntity<Offer> result = offerController.getOffer(0);

        Offer testOffer = new Offer().builder()
                .description("I am Get test")
                .name("GetTest")
                .price(123)
                .isCanceled(false)
                .expireDateTime(LocalDateTime.of(2022,
                        Month.MARCH, 1, 0, 0, 0))
                .build();

        assertThat(result.getBody()).isEqualTo(testOffer);
    }

    @Test
    void addOffer() {
        offerController = new OfferController(offerService);

        when(offerService.addOffer(anyString(), anyInt(), anyString(), anyBoolean(), anyString())).thenReturn(1);

        String newId = offerController.addOffer("AddTest", 123, "2022-04-01T00:00:00", true, "I am Add test").getBody();

        assertThat(newId).isEqualTo("Your Offer has been created, ID:1");
    }

    @Test
    void cancelOffer()
    {
        offerController = new OfferController(offerService);

        offerController.cancelOffer(1);

        verify(offerService, times(1)).cancelOffer(1);
    }


}