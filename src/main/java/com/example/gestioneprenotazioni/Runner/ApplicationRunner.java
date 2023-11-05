package com.example.gestioneprenotazioni.Runner;

import com.example.gestioneprenotazioni.Daos.BuildingDao;
import com.example.gestioneprenotazioni.Daos.ReservationDao;
import com.example.gestioneprenotazioni.Daos.StationDao;
import com.example.gestioneprenotazioni.Daos.UserDao;
import com.example.gestioneprenotazioni.Entities.Building;
import com.example.gestioneprenotazioni.Entities.Reservation;
import com.example.gestioneprenotazioni.Entities.Station;
import com.example.gestioneprenotazioni.Entities.User;
import com.example.gestioneprenotazioni.Enums.Tipo;
import com.example.gestioneprenotazioni.Exceptions.PersonalException;
import com.example.gestioneprenotazioni.Repositories.BuildingRepository;
import com.example.gestioneprenotazioni.Repositories.StationRepository;
import com.example.gestioneprenotazioni.Repositories.UserRepository;
import com.example.gestioneprenotazioni.Services.ReservationService;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;

@Component
@Slf4j
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private UserDao userDAO;
    @Autowired
    private BuildingDao buildingDAO;
    @Autowired
    private StationDao stationDAO;
    @Autowired
    private ReservationService reservationDAO;

    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    StationRepository stationRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker(Locale.ITALY);
        for(int i =0;i<=20;i++){
          //  User genericUser = User.builder().build();
            //  userDAO.save(genericUser);
            //Building genericBuilding = Building.builder().build();
            //buildingDAO.save(genericBuilding);
            Reservation reservation =  new Reservation(
                    LocalDate.now().plusDays(2),
           stationRepository.findRandomStation(),
          userRepository.findRandomUser()
          );
            try{
                reservationDAO.save(reservation);
            }catch (PersonalException p){
                System.err.println(p.getMessage());
            }

        }


        System.out.println("****************************** FIND ALL ***********************");


        System.out.println("****************************** FIND BY ID ***********************");


        System.out.println("****************************** FIND BY ID AND DELETE ***********************");


        System.out.println("****************************** FIND BY ID AND UPDATE ***********************");



        System.out.println("****************************** COUNT ***********************");


        System.out.println("****************************** FILTER BY SURNAME ***********************");


        System.out.println("****************************** FILTER BY NAME AND SURNAME ***********************");


        System.out.println("****************************** FILTER BY NAMES IN A LIST ***********************");


        System.out.println("****************************** FILTER BY AGE GREATER THAN ***********************");

    }
}