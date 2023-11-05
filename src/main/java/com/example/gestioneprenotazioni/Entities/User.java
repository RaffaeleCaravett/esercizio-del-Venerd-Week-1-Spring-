package com.example.gestioneprenotazioni.Entities;

import com.github.javafaker.Faker;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;


@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "UserBuilder")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    private String username;
    private String nome_completo;
    private String email;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Reservation> reservationList;
    public static class UserBuilder{
        private Faker faker = new Faker(Locale.ITALY);
        private String username = faker.name().username();
        private String nome_completo = faker.name().firstName() + " " + faker.name().lastName();
        private String email = faker.internet().emailAddress();

    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setNomeCompleto(String nome_completo) {
        this.nome_completo= nome_completo;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
