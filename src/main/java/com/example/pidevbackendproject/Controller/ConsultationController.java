package com.example.pidevbackendproject.Controller;

import com.example.pidevbackendproject.Dto.consultationDto;
import com.example.pidevbackendproject.entities.Consultation;
import com.example.pidevbackendproject.services.IConsultationServise;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultation")
@AllArgsConstructor

public class ConsultationController {
    IConsultationServise consultationService;

    @PostMapping("/add")
    public Consultation add(@RequestBody Consultation consultation) {
        System.out.println("Controller hit");
        return consultationService.addConsultation(consultation);
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

}
