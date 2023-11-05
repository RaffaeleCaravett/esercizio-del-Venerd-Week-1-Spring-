package com.example.gestioneprenotazioni.Entities;

import com.github.javafaker.Faker;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Locale;

@Entity
@Table(name = "reservations")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "ReservationBuilder")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate data_prenotazione;
    private LocalDate data_scadenza;
    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Reservation(LocalDate data_prenotazione,  Station station, User user) {
        this.data_prenotazione = data_prenotazione;
        this.data_scadenza = data_prenotazione.plusDays(1);
        this.station = station;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", data_prenotazione=" + data_prenotazione +
                ", data_scadenza=" + data_scadenza +
                '}';
    }
}
