package com.example.gestioneprenotazioni.Repositories;

import com.example.gestioneprenotazioni.Entities.Building;
import com.example.gestioneprenotazioni.Entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Query(value = "SELECT * FROM stations ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Station findRandomStation();
}
