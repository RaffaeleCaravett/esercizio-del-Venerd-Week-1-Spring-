package com.example.gestioneprenotazioni.Repositories;

import com.example.gestioneprenotazioni.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
