package com.fis.code.screen.merchantoffer.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fis.code.screen.merchantoffer.model.Offer;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Component
public class OfferDaoImpl implements OfferDao{

    private final ObjectMapper mapper;

    public OfferDaoImpl(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    @Override
    public int insertNewOffer(Offer offer){

        int newId = 0;

        try{
            File jsonFile = new File("offer.json");

            HashMap<Integer,Offer> map = new HashMap<>();

            if(jsonFile.exists())
            {
                map = mapper.readValue(jsonFile, new TypeReference<>(){});

                if(map != null && !map.isEmpty())
                {
                    newId = map.size();
                }
            }

            map.put(newId, offer);

            mapper.writeValue(jsonFile, map);
        } catch(IOException e)
        {
            e.printStackTrace();
        }

        return newId;
    }

    @Override
    public boolean cancelOffer(int id) {

        try{
            File jsonFile = new File("offer.json");

            if(jsonFile.exists())
            {
                HashMap<Integer,Offer> map = mapper.readValue(jsonFile, new TypeReference<>(){});

                if(map != null && !map.isEmpty())
                {
                    if(map.containsKey(id)){
                        map.get(id).setCanceled(true);
                        mapper.writeValue(jsonFile, map);
                        return true;
                    }
                }
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Offer getOffer(int id) {

        Offer offer = new Offer();

        try{
            File jsonFile = new File("offer.json");

            if(jsonFile.exists())
            {
                HashMap<Integer,Offer> map = mapper.readValue(jsonFile, new TypeReference<>(){});

                if(map != null && !map.isEmpty())
                {
                    if(map.containsKey(id)){
                        offer = map.get(id);
                    }
                }
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        }

        return offer;
    }
}
