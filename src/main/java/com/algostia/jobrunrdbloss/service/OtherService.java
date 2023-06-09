package com.algostia.jobrunrdbloss.service;

import com.algostia.jobrunrdbloss.model.Dummy;
import com.algostia.jobrunrdbloss.repository.DummyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OtherService {

    @Autowired
    private DummyRepository dummyRepository;

    @Scheduled(fixedDelay = 5000)
    public void doSomething() {
        Dummy dummy;

        List<Dummy> all = dummyRepository.findAll();
        if (all.size() == 0) {
            dummy = new Dummy();
            dummy.setValue(0);
        } else {
            dummy = all.get(0);
            dummy.setValue(dummy.getValue() + 1);
        }

        log.info("dummy={}", dummy.getValue());

        dummyRepository.save(dummy);
    }
}
