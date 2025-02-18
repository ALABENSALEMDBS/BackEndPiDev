package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Clubs;
import com.example.pidevbackendproject.entities.Users;
import com.example.pidevbackendproject.services.IUsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Users")
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UsersRestController {
    IUsersService usersService;

    @Operation(description = "Ajouter un User")
    @PostMapping("/add-user")
    public Users addUsers(@RequestBody Users u) {
        return usersService.addUsers(u);
    }

    @Operation(description = "récupérer toutes les users de la base de données")
    @GetMapping(value = "/retrieve-all-user")
    public List<Users> getAllUsers() {
        List<Users> users= usersService.getAllUsers();
        return users;
    }

    @Operation(description = "récupérer les users de la base de données by ID")
    @GetMapping("/retrieve-user/{user-id}")
    public Users retrieveUsers(@PathVariable("user-id") int idUsers) {
        Users users =usersService.getUsersById(idUsers);
        return users;
    }

    @Operation(description = "Supprimer user by ID")
    @DeleteMapping("/remove-user/{user-id}")
    public void deleteUsers(@PathVariable("user-id") int idUsers) {
        usersService.deleteUsers(idUsers);
    }

    @Operation(description = "Modifer user")
    @PutMapping("/modify-user")
    public Users modifyUsers(@RequestBody Users u) {
        Users users= usersService.modifyUsers(u);
        return users;
    }
}
