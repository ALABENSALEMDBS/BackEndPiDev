package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Users;

import java.util.List;

public interface IUsersService {
    Users addUsers(Users user);
    void deleteUsers(int idUser);
    Users modifyUsers(int idUser,Users user);
    List<Users> getAllUsers();
    Users getUsersById(int idUser);
}
