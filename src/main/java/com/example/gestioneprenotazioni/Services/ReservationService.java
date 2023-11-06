package com.example.gestioneprenotazioni.Services;

import com.example.gestioneprenotazioni.Daos.ReservationDao;
import com.example.gestioneprenotazioni.Entities.Reservation;
import com.example.gestioneprenotazioni.Entities.Station;
import com.example.gestioneprenotazioni.Exceptions.PersonalException;
import com.example.gestioneprenotazioni.Repositories.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
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
        System.out.println(reservation.getStation().getNumero_massimo_partecipanti()-reservation.getStation().getReservationList().size());
        if(reservation.getStation().getReservationList().size()<reservation.getStation().getNumero_massimo_partecipanti()){
            reservation.getStation().setItFree(true);
            stationService.save(reservation.getStation());
            reservation.getUser().getReservationList().forEach(r->{
                if(r.getData_prenotazione().equals(reservation.getData_prenotazione())){
                   throw new PersonalException("La postazione "+reservation.getStation().getCodice_univoco() + " è libera ma l'utente " +
                           reservation.getUser().getId() +" ha già una prenotazione in questa data.");
                }
            });
                reservationRepo.save(reservation);

if(reservation.getStation().getNumero_massimo_partecipanti()-reservation.getStation().getReservationList().size()==1){
   reservation.getStation().setItFree(false);
   stationService.save(reservation.getStation());
    System.out.println("La stazione " +reservation.getStation().getCodice_univoco() + " da ora è al completo");
    System.out.println("Numero prenotazioni : " +reservation.getStation().getReservationList().size());
    System.out.println("Numero partecipanti massimo : " +reservation.getStation().getNumero_massimo_partecipanti());
}
        }else{
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
        try {
            Reservation reservation = this.findById(id);
            Station station = reservation.getStation();
            station.setItFree(true);
            stationService.save(station);
            reservationRepo.delete(reservation);
            System.out.println("La prenotazione con ID " + id + " è stata eliminata correttamente.");
        }catch (PersonalException p){
            System.err.println(p.getMessage());
        }
        }

    @Override
    public Reservation findById(long id) {
        Optional<Reservation> optionalReservation = reservationRepo.findById(id);
        if (optionalReservation.isPresent()) {
                return optionalReservation.get();
            }else{
                throw new PersonalException("La prenotazione non è stata trovata");
            }
    }

    @Override
    public List<Reservation> findAll() {
            return reservationRepo.findAll().stream().toList();

    }
}