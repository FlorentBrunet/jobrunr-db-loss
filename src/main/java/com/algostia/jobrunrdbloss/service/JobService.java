package com.algostia.jobrunrdbloss.service;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.spring.annotations.Recurring;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobService {

    private long counter = 0;

    @Job(name = "Job to say hello")
    @Recurring(interval = "PT10S")
    public void sayHello() {
        log.info("Hello from JobRunr {}", counter);
        ++counter;
    }
}
