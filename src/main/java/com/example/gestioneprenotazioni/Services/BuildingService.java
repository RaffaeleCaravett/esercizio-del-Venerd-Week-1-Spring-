package com.example.gestioneprenotazioni.Services;

import com.example.gestioneprenotazioni.Daos.BuildingDao;
import com.example.gestioneprenotazioni.Entities.Building;
import com.example.gestioneprenotazioni.Repositories.BuildingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BuildingService implements BuildingDao {
    @Autowired
    private BuildingRepository buildingRepo;
    @Override
    public void save(Building building) {
       buildingRepo.save(building);
    }

    @Override
    public void findByIdAndUpdate(long id, Building building){
        Building found = this.findById(id);
        // found.get().setEmail(user.getEmail());
        // 3. Risalvo lo user modificato
        //usersRepo.save(found.get());
        //log.info("User con id " + id + " aggiornato con successo!");
    }

    @Override
    public void findByIdAndDelete(long id) {
        Building found = this.findById(id);
        buildingRepo.delete(found);
        log.info("User con id " + id + " eliminato con successo!");
    }

    @Override
    public Building findById(long id) {
        return buildingRepo.findById(id).get();
    }

    @Override
    public List<Building> findAll() {
        return buildingRepo.findAll().stream().toList();
    }
}
