package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Users;
import com.example.pidevbackendproject.repositories.UsersRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersImpService implements IUsersService {
    UsersRepo usersRepo;
    public Users addUsers(Users user) {
        return usersRepo.save(user);
    }

    public void deleteUsers(int idUser) {
     usersRepo.deleteById(idUser);
    }

    public Users modifyUsers(Users user) {
        return usersRepo.save(user);
    }

    public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }

    public Users getUsersById(int idUser) {
        return usersRepo.findById(idUser).get();
    }
}
