package com.algostia.jobrunrdbloss.repository;

import com.algostia.jobrunrdbloss.model.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyRepository extends JpaRepository<Dummy, Long> {
}
