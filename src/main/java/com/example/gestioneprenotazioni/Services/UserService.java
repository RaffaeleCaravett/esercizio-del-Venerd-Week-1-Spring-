package com.example.gestioneprenotazioni.Services;

import com.example.gestioneprenotazioni.Daos.UserDao;
import com.example.gestioneprenotazioni.Entities.User;
import com.example.gestioneprenotazioni.Repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserDao {

    @Autowired
    private UserRepository usersRepo;
    @Override
    public void save(User user) {
        // eventuale logica addizionale custom..
        usersRepo.save(user);
    }

    @Override
    public void findByIdAndUpdate(long id, User user){
        User found = this.findById(id);
       // found.get().setEmail(user.getEmail());
        // 3. Risalvo lo user modificato
        //usersRepo.save(found.get());
        //log.info("User con id " + id + " aggiornato con successo!");
    }

    @Override
    public void findByIdAndDelete(long id) {
        User found = this.findById(id);
        usersRepo.delete(found);
        log.info("User con id " + id + " eliminato con successo!");
    }

    @Override
    public User findById(long id) {
        return usersRepo.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return usersRepo.findAll().stream().toList();
    }
}
