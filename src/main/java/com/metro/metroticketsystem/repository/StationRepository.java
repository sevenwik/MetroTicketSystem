package com.metro.metroticketsystem.repository;

import com.metro.metroticketsystem.models.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
    Station findByName(String name);
}