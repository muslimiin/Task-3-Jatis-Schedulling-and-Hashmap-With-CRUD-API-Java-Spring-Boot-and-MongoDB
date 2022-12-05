package com.week2.mongodb.service;


import com.week2.mongodb.model.Presiden;
import com.week2.mongodb.repository.PresidenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HashmapService {

    @Autowired
    private PresidenRepository presidenRepository;

    HashMap<Long, Presiden> presidenHashMap = new HashMap<>();

    public HashMap reloadHashmap(){
        Iterable<Presiden> presiden =  presidenRepository.findAll();

        presiden.forEach((Presiden pres) -> {
            presidenHashMap.put(pres.getId(), pres);
        });

        return presidenHashMap;
    }

    public void showHashmap(){
        for(Map.Entry pres: presidenHashMap.entrySet()){
            Presiden pr = (Presiden) pres.getValue();
            System.out.println(pres.getKey() + ": "+ pr);
        }
    }
}
