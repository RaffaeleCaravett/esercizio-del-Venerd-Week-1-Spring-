package com.example.gestioneprenotazioni.Daos;

import com.example.gestioneprenotazioni.Entities.Station;
import com.example.gestioneprenotazioni.Entities.User;

import java.util.List;

public interface StationDao {
    public void save(Station station);

    public void findByCodiceUnivocoAndUpdateIsItFree(long codiceUnivoco);

    public void findByCodiceUnivocoAndDelete(long codiceUnivoco);

    public Station findByCodiceUnivoco(long codiceUnivoco);

    public List<Station> findAll();
}
