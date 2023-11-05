package com.example.gestioneprenotazioni.Services;

import com.example.gestioneprenotazioni.Daos.ReservationDao;
import com.example.gestioneprenotazioni.Entities.Reservation;
import com.example.gestioneprenotazioni.Entities.Station;
import com.example.gestioneprenotazioni.Exceptions.PersonalException;
import com.example.gestioneprenotazioni.Repositories.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
public class ReservationService implements ReservationDao {
    @Autowired
    private ReservationRepository reservationRepo;
    @Autowired
    private StationService stationService;

    @Override
    public void save(Reservation reservation) {
        if(reservation.getStation().getReservationList().size()<reservation.getStation().getNumero_massimo_partecipanti()){
        if(reservation.getStation().isItFree()){
            reservation.getUser().getReservationList().forEach(r->{
                if(r.getData_prenotazione().equals(reservation.getData_prenotazione())){
                   throw new PersonalException("La postazione "+reservation.getStation().getCodice_univoco() + " è libera ma l'utente " +
                           reservation.getUser().getId() +" ha già una prenotazione in questa data.");
                }
            });
                reservationRepo.save(reservation);
if(reservation.getStation().getNumero_massimo_partecipanti()-reservation.getStation().getReservationList().size()==0){
    stationService.findByCodiceUnivocoAndUpdateIsItFree(reservation.getStation().getCodice_univoco());
    System.out.println("La stazione " +reservation.getStation().getCodice_univoco() + " da ora è al completo");
    System.out.println("Numero prenotazioni : " +reservation.getStation().getReservationList().size());
    System.out.println("Numero partecipanti massimo : " +reservation.getStation().getNumero_massimo_partecipanti());
}
        }else{
            throw new PersonalException("La postazione "+reservation.getStation().getCodice_univoco() + " non è libera.");
        }}else{
            throw new PersonalException("La postazione " + reservation.getStation().getCodice_univoco() + " ha già raggiunto il numero massimo di prenotazioni.\n"
                    + "Numero prenotazioni : " + reservation.getStation().getReservationList().size() + "\n"
                    + "Numero partecipanti massimo : " + reservation.getStation().getNumero_massimo_partecipanti());
        }
    }

    @Override
    public void findByIdAndUpdate(long id, Reservation r) {
        Reservation found = this.findById(id);
        // found.get().setEmail(user.getEmail());
        // 3. Risalvo lo user modificato
        //usersRepo.save(found.get());
        //log.info("User con id " + id + " aggiornato con successo!");
    }

    @Override
    public void findByIdAndDelete(long id) {
        Reservation found = this.findById(id);
        reservationRepo.delete(found);
    }

    @Override
    public Reservation findById(long id) {
        return reservationRepo.findById(id).get();
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepo.findAll().stream().toList();
    }
}