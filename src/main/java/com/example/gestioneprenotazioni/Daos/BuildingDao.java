package com.example.gestioneprenotazioni.Daos;

import com.example.gestioneprenotazioni.Entities.Building;
import com.example.gestioneprenotazioni.Entities.User;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

import java.util.List;

public interface BuildingDao {
    public void save(Building building);

    public void findByIdAndUpdate(long id, Building building);


    public void findByIdAndDelete(long id);

    public Building findById(long id);

    public List<Building> findAll();
}
