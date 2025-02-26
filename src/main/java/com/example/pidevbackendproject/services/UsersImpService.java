package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.entities.Users;
import com.example.pidevbackendproject.repositories.UsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersImpService implements IUsersService {
    UsersRepo usersRepo;
    public Users addUsers(Users user) {
        return usersRepo.save(user);
    }

    public void deleteUsers(int idUser) {
     usersRepo.deleteById(idUser);
    }

    public Users modifyUsers(int idUser,Users user) {
        Optional<Users> optionalUser = usersRepo.findById(idUser);

        if (!optionalUser.isPresent()) {
            throw new RuntimeException("user non trouvé");
        }

        Users existingUser = optionalUser.get();
        existingUser.setNameUsers(user.getNameUsers());
        existingUser.setPrenomUser(user.getPrenomUser());
        existingUser.setEmailUser(user.getEmailUser());
        existingUser.setTelephoneUser(user.getTelephoneUser());

        return usersRepo.save(existingUser);
    }

    public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }

    public Users getUsersById(int idUser) {
        return usersRepo.findById(idUser).get();
    }
}
