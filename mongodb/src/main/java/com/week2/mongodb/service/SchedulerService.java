package com.week2.mongodb.service;


import com.week2.mongodb.jms.JmsProducer;
import com.week2.mongodb.model.Presiden;
import com.week2.mongodb.repository.PresidenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Optional;

@Component
@Slf4j
public class SchedulerService {

    @Autowired
    private PresidenRepository presidenRepository;

    @Autowired
    private JmsProducer jmsProducer;


    @Scheduled(cron = "0 11 21 * * ?")
    public void ScheduledTask(){

        SimpleDateFormat dateFormat = new SimpleDateFormat();

        Long id = 2L;

        Optional<Presiden> presidenOptional = presidenRepository.findById(id);

        if (presidenOptional.isPresent()){
            presidenOptional.get().setNama("Anies Baswedan Lover");
            presidenOptional.get().setBerat_badan(100);
            presidenOptional.get().setUmur(60);
            presidenOptional.get().setTinggi_badan(180);

            presidenRepository.save(presidenOptional.get());

        }

        jmsProducer.sendMessage("Reload HashMap");
    }


}
