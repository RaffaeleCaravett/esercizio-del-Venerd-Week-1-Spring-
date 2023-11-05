package com.example.gestioneprenotazioni.Services;

import com.example.gestioneprenotazioni.Daos.StationDao;
import com.example.gestioneprenotazioni.Entities.Station;
import com.example.gestioneprenotazioni.Repositories.StationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StationService implements StationDao {
    @Autowired
    private StationRepository stationRepo;
    @Override
    public void save(Station station) {
        stationRepo.save(station);
    }

    @Override
    public void findByCodiceUnivocoAndUpdateIsItFree(long codiceUnivoco) {
        Station found = this.findByCodiceUnivoco(codiceUnivoco);
        found.setItFree(!found.isItFree());
        // 3. Risalvo lo user modificato
        stationRepo.save(found);
        //log.info("User con id " + id + " aggiornato con successo!");
    }

    @Override
    public void findByCodiceUnivocoAndDelete(long codiceUnivoco) {
        Station found = this.findByCodiceUnivoco(codiceUnivoco);
        stationRepo.delete(found);
    }

    @Override
    public Station findByCodiceUnivoco(long codiceUnivoco) {
        return stationRepo.findById(codiceUnivoco).get();
    }



    @Override
    public List<Station> findAll() {
        return stationRepo.findAll().stream().toList();
    }
}
