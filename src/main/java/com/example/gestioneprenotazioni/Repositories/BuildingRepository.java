package com.example.gestioneprenotazioni.Repositories;

import com.example.gestioneprenotazioni.Entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    @Query(value = "SELECT * FROM buildings ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Building findRandomBuilding();
}
