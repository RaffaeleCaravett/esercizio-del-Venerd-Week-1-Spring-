package com.example.gestioneprenotazioni.Repositories;

import com.example.gestioneprenotazioni.Entities.Station;
import com.example.gestioneprenotazioni.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Specializzazione di @Component dedicata alle interazioni col DB
public interface UserRepository extends JpaRepository<User, Long> {

    // Estendendo JpaRepository eredito tutti i metodi CRUD

    // DOCUMENTAZIONE
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    @Query(value = "SELECT * FROM users ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    User findRandomUser();
}
