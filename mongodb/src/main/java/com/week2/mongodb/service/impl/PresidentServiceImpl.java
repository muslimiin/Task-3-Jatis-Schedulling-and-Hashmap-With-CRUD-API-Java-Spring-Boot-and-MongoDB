package com.week2.mongodb.service.impl;

import com.week2.mongodb.model.Presiden;
import com.week2.mongodb.repository.PresidenRepository;
import com.week2.mongodb.service.PresidenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class PresidentServiceImpl implements PresidenService {

    @Autowired
    private PresidenRepository presidenRepository;

    @Override
    public Presiden createPresiden(Presiden presiden) {
            return presidenRepository.save(presiden);
    }

    @Override
    public Presiden updatePresiden(Presiden presiden) {

        Optional<Presiden> presidenOptional = presidenRepository.findById(presiden.getId());

            Presiden presidenUpdate = presidenOptional.get();
            presidenUpdate.setNama(presiden.getNama());
            presidenUpdate.setUmur(presiden.getUmur());
            presidenUpdate.setTinggi_badan(presiden.getTinggi_badan());
            presidenUpdate.setBerat_badan(presiden.getBerat_badan());
            presidenUpdate.setSebelum_jadipresiden(presiden.getSebelum_jadipresiden());
            presidenRepository.save(presidenUpdate);
            return presidenUpdate;



    }

    @Override
    public List<Presiden> getAllPresiden() {
        return this.presidenRepository.findAll();
    }

    @Override
    public Presiden getPresidenById(Long presidenId) {
        Optional<Presiden> presidenOptional = presidenRepository.findById(presidenId);

            return presidenOptional.get();

    }

    @Override
    public Presiden deletePresiden(Long id) {
        Optional<Presiden> presidenOptional = presidenRepository.findById(id);
            this.presidenRepository.delete(presidenOptional.get());

        return null;
    }


}
