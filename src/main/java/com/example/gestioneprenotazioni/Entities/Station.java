package com.example.gestioneprenotazioni.Entities;

import com.example.gestioneprenotazioni.Enums.Tipo;
import com.example.gestioneprenotazioni.Repositories.BuildingRepository;
import com.example.gestioneprenotazioni.Services.BuildingService;
import com.github.javafaker.Faker;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "stations")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "StationBuilder")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codice_univoco;
    private String descrizione;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    private int numero_massimo_partecipanti;
    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;
    private boolean isItFree;
    @OneToMany(mappedBy = "station", fetch = FetchType.EAGER)
    private List<Reservation> reservationList;

    public Station(String descrizione, Tipo tipo, int numero_massimo_partecipanti, Building building, boolean isItFree) {
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.numero_massimo_partecipanti = numero_massimo_partecipanti;
        this.building = building;
        this.isItFree = isItFree;
    }

    public void setItFree(boolean itFree) {
        isItFree = itFree;
    }

    public boolean getIsItFree() {
        return isItFree;
    }
}
