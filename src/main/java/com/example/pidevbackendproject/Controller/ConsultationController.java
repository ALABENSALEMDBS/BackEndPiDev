package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.Dto.consultationDto;
import com.example.pidevbackendproject.entities.Consultation;
import com.example.pidevbackendproject.services.IConsultationServise;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultation")
@AllArgsConstructor

public class ConsultationController {
    IConsultationServise consultationService;

    @PostMapping("/add/{idn}")
    public Consultation addConsultation(@RequestBody Consultation consultation, @PathVariable int idn) {
        return consultationService.addConsultation(consultation, idn);
    }

    @GetMapping("/all")
    public List<consultationDto> getAll() {
        return consultationService.getAllConsultations();
    }

    @PutMapping ("/update/{id}")
    public Consultation modify(@RequestBody Consultation consultation ,@PathVariable Long id ) {
        System.out.println("Controller hit");
        return consultationService.modifyconsultation(consultation,id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable int id) {
        try {
            consultationService.deleteConsultation(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

}
