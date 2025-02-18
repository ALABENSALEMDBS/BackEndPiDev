package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.entities.Messages;
import com.example.pidevbackendproject.services.IMessagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestion Messages")
@RestController
@AllArgsConstructor
@RequestMapping("/messages")
public class MessagesRestController {
    IMessagesService messagesService;

    @Operation(description = "Ajouter un Messages")
    @PostMapping("/add-messages")
    public Messages addMessages(@RequestBody Messages mg) {
        return messagesService.addMessages(mg);
    }

    @Operation(description = "récupérer toutes les messages de la base de données")
    @GetMapping(value = "/retrieve-all-messages")
    public List<Messages> getAllMessages() {
        List<Messages> messages= messagesService.getAllMessages();
        return messages;
    }

    @Operation(description = "récupérer les messages de la base de données by ID")
    @GetMapping("/retrieve-messages/{messages-id}")
    public Messages retrieveMessages(@PathVariable("messages-id") int idMessages) {
        Messages messages = messagesService.getMessagesById(idMessages);
        return messages;
    }

    @Operation(description = "Supprimer messages by ID")
    @DeleteMapping("/remove-messages/{messages-id}")
    public void deleteMessages(@PathVariable("messages-id") int idMessages) {
        messagesService.deleteMessages(idMessages);
    }

    @Operation(description = "Modifer messages")
    @PutMapping("/modify-messages")
    public Messages modifyMessages(@RequestBody Messages mg) {
        Messages messages= messagesService.modifyMessages(mg);
        return messages;
    }
}
