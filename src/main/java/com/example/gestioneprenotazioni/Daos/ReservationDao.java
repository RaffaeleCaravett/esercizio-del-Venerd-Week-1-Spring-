package com.example.gestioneprenotazioni.Daos;

import com.example.gestioneprenotazioni.Entities.Reservation;
import com.example.gestioneprenotazioni.Entities.Station;
import com.example.gestioneprenotazioni.Entities.User;

import java.util.List;

public interface ReservationDao {
    public void save(Reservation reservation);

    public void findByIdAndUpdate(long id, Reservation reservation);

    public void findByIdAndDelete(long id);

    public Reservation findById(long id);

    public List<Reservation> findAll();
}
