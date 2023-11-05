package com.example.gestioneprenotazioni.Entities;


import com.github.javafaker.Faker;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "buildings")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "BuildingBuilder")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String indirizzo;
    private String città;
    @OneToMany(mappedBy = "building", fetch = FetchType.EAGER)
    @OrderBy("id ASC")
    private List<Station> stationsList;

    public static class BuildingBuilder{
        private Faker faker = new Faker(Locale.ITALY);
        private String name = faker.name().title();
        private String indirizzo = faker.address().fullAddress();
        private String città=faker.address().city();
    }
}
