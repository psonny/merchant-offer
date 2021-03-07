package com.fis.code.screen.merchantoffer.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fis.code.screen.merchantoffer.BaseTest;
import com.fis.code.screen.merchantoffer.model.Offer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class OfferDaoTest extends BaseTest {

    private OfferDao offerDao;

    @Mock
    private ObjectMapper mapper;

    @Test
    void insertNewOffer() throws IOException {

        offerDao = new OfferDaoImpl(mapper);

        Offer offer = new Offer().builder()
                .name("Test")
                .description("I am test")
                .expireDateTime(LocalDateTime.now().plusMonths(1))
                .price(123)
                .isCanceled(false)
                .build();

        when(mapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(new HashMap<>());
        doNothing().when(mapper).writeValue(any(File.class), eq(HashMap.class));

        int id = offerDao.insertNewOffer(offer);

        assertThat(id).isEqualTo(0);
    }

    @Test
    void cancelOffer() throws IOException {

        offerDao = new OfferDaoImpl(mapper);
        File jsonFile = new File("offer.json");

        if(jsonFile.exists())
        {
            Offer offer = new Offer().builder()
                    .name("Test")
                    .description("I am test")
                    .expireDateTime(LocalDateTime.now().plusMonths(1))
                    .price(123)
                    .isCanceled(false)
                    .build();

            HashMap<Integer,Offer> map = new HashMap<>();
            map.put(0, offer);

            when(mapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(map);
            doNothing().when(mapper).writeValue(any(File.class), eq(HashMap.class));

            boolean valid = offerDao.cancelOffer(0);

            assertThat(valid).isTrue();
        }
        else
        {
            boolean valid = offerDao.cancelOffer(0);

            assertThat(valid).isFalse();

        }
    }

    @Test
    void getOffer() throws IOException {

        offerDao = new OfferDaoImpl(mapper);
        File jsonFile = new File("offer.json");

        if(jsonFile.exists())
        {
            Offer offer = new Offer().builder()
                    .name("Test")
                    .description("I am test")
                    .expireDateTime(LocalDateTime.now().plusMonths(1))
                    .price(123)
                    .isCanceled(false)
                    .build();

            HashMap<Integer,Offer> map = new HashMap<>();
            map.put(0, offer);

            when(mapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(map);
            doNothing().when(mapper).writeValue(any(File.class), eq(HashMap.class));

            Offer offerActual = offerDao.getOffer(0);

            assertThat(offerActual).isEqualTo(offer);
        }
        else
        {
            Offer offerActual = offerDao.getOffer(0);

            assertThat(offerActual).isEqualTo(new Offer());
        }
    }
}