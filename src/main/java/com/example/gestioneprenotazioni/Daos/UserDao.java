package com.example.gestioneprenotazioni.Daos;

import com.example.gestioneprenotazioni.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    public void save(User user);

    public void findByIdAndUpdate(long id, User user);

    public void findByIdAndDelete(long id);

    public User findById(long id);

    public List<User> findAll();

}
