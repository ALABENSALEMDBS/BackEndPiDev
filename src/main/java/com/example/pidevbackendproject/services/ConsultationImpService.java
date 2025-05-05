package com.example.pidevbackendproject.services;

import com.example.pidevbackendproject.Dto.consultationDto;
import com.example.pidevbackendproject.entities.Consultation;

import com.example.pidevbackendproject.entities.ExerciceRetablissements;
import com.example.pidevbackendproject.entities.Joueurs;
import com.example.pidevbackendproject.repositories.ConsultationRepo;
import com.example.pidevbackendproject.repositories.JoueursRepo;
import com.example.pidevbackendproject.repositories.UsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConsultationImpService implements IConsultationServise {
    ConsultationRepo consultationRepo;
    JoueursRepo joueursRepo;
    UsersRepo usersRepo;
    EmailService emailService;
    public Consultation addConsultation(Consultation c, int id) {
        Joueurs j=joueursRepo.findById(id).get();
        c.setJoueur(j);
        sendRecoveryconsultationEmail(j,c);
        return consultationRepo.save(c);
    }

    public List<consultationDto> getAllConsultations() {
        return consultationRepo.findAllWithJoueurFullNameConsultation();
    }

    public Consultation modifyconsultation(Consultation c, Long idc) {

        // Récupérer la consultation existante
        Consultation existing = consultationRepo.findById(idc)
                .orElseThrow(() -> new RuntimeException("Consultation introuvable avec l'id : " + idc));

        // Vérifier si une autre consultation existe à la même date
        boolean isDateTaken = consultationRepo.existsByDateConsultationAndIdNot(c.getDateConsultation(), existing.getId());

        if (isDateTaken) {
            throw new RuntimeException("La date " + c.getDateConsultation() + " est déjà prise par un autre joueur.");
        }

        // Modifier la date de consultation
        existing.setDateConsultation(c.getDateConsultation());

        // Modifier d'autres informations si nécessaire, par exemple la description
        existing.setDescription(c.getDescription());

        // Récupérer le joueur de la consultation modifiée
        Joueurs joueur = existing.getJoueur();

        // Envoyer un email d'information sur la modification
        sendRecoveryconsultationModifierEmail(joueur, existing);

        // Sauvegarder la consultation modifiée
        return consultationRepo.save(existing);
    }


    public void deleteConsultation(long idConsultation) {

        // Récupérer la consultation existante par son ID
        Consultation consultation = consultationRepo.findById(idConsultation)
                .orElseThrow(() -> new RuntimeException("Consultation introuvable avec l'id : " + idConsultation));

        // Si nécessaire, vous pouvez également supprimer l'association avec le joueur (selon votre logique métier)
        Joueurs joueur = consultation.getJoueur(); // Récupérer le joueur associé à la consultation

        if (joueur != null) {
            // Si le joueur est associé à la consultation, vous pouvez faire des actions ici,
            // comme supprimer l'association ou effectuer des vérifications supplémentaires.
            joueur.getConsultations().remove(consultation); // Par exemple, retirer cette consultation de la liste des consultations du joueur
            joueursRepo.save(joueur); // Sauvegarder les modifications du joueur
        }

        // Supprimer la consultation de la base de données
        consultationRepo.deleteById(idConsultation);
    }


    private void sendRecoveryconsultationEmail(Joueurs joueur, Consultation consultation) {
        String html = """
<html>
<body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
    <div style="max-width: 600px; margin: auto; background-color: white; border-radius: 10px; padding: 30px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
        <div style="text-align: center;">
            <img src="https://resize.elle.fr/article/var/oa/content/images/tests/original/1252_test-1670867124.jpg" alt="Tactic Foot Logo" style="width: 120px; margin-bottom: 20px;" />
            <h2 style="color: #2c3e50;">Hello %s 👋</h2>
        </div>
        <p style="font-size: 16px; color: #555;">
            A <strong>medical consultation</strong> has been scheduled for you via the <strong>Tactic Foot</strong> app.
        </p>
        <p style="font-size: 16px; color: #555;">
            <strong>Details of your appointment:</strong>
        </p>
        <ul style="font-size: 16px; color: #555; line-height: 1.6; padding-left: 20px;">
            <li><strong>Date:</strong> %s</li>
            <li><strong>Program:</strong> %s</li>
        </ul>
        <p style="font-size: 16px; color: #555;">
            Please note this date and make sure to show up at the scheduled time.
        </p>
        <p style="font-size: 14px; color: #777; margin-top: 30px;">
            If you have any questions, our team is available to assist you.
        </p>
        <div style="text-align: right; margin-top: 40px;">
            <img src="https://i.imgur.com/RK2znLl.png" alt="Signature" style="width: 100px;" />
        </div>
        <hr style="margin: 30px 0;">
        <p style="font-size: 13px; color: #999; text-align: center;">
            &copy; 2025 Tactic Foot | 📧 <a href='mailto:TACTICFOOT@medisport.tn'>TACTICFOOT@medisport.tn</a> | 📞 +216 20 345 678<br>
            🌐 <a href='https://www.medisport.tn' style='color:#2c3e50;'>www.medisport.tn</a>
        </p>
    </div>
</body>
</html>
""".formatted(joueur.getNameUsers(), consultation.getDateConsultation(), consultation.getDescription());




        emailService.sendMail(joueur.getEmailUser(), html);
    }

    private void sendRecoveryconsultationModifierEmail(Joueurs joueur, Consultation consultation) {
        String html = """
<html>
<body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
    <div style="max-width: 600px; margin: auto; background-color: white; border-radius: 10px; padding: 30px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
        <div style="text-align: center;">
            <img src="https://resize.elle.fr/article/var/oa/content/images/tests/original/1252_test-1670867124.jpg" alt="Tactic Foot Logo" style="width: 120px; margin-bottom: 20px;" />
            <h2 style="color: #2c3e50;">Hello %s 👋</h2>
        </div>
        <p style="font-size: 16px; color: #555;">
            We are writing to inform you that there has been a change in your <strong>medical consultation</strong> scheduled via the <strong>Tactic Foot</strong> app.
        </p>
        <p style="font-size: 16px; color: #555;">
            <strong>Updated details of your appointment:</strong>
        </p>
        <ul style="font-size: 16px; color: #555; line-height: 1.6; padding-left: 20px;">
            <li><strong>Date:</strong> %s</li>
            <li><strong>Program:</strong> %s</li>
        </ul>
        <p style="font-size: 16px; color: #555;">
            Please note this change and ensure that you attend the consultation at the new scheduled time.
        </p>
        <p style="font-size: 14px; color: #777; margin-top: 30px;">
            If you have any questions or need assistance, please don't hesitate to contact our team.
        </p>
        <div style="text-align: right; margin-top: 40px;">
            <img src="https://i.imgur.com/RK2znLl.png" alt="Signature" style="width: 100px;" />
        </div>
        <hr style="margin: 30px 0;">
        <p style="font-size: 13px; color: #999; text-align: center;">
            &copy; 2025 Tactic Foot | 📧 <a href='mailto:TACTICFOOT@medisport.tn'>TACTICFOOT@medisport.tn</a> | 📞 +216 20 345 678<br>
            🌐 <a href='https://www.medisport.tn' style='color:#2c3e50;'>www.medisport.tn</a>
        </p>
    </div>
</body>
</html>
""".formatted(joueur.getNameUsers(), consultation.getDateConsultation(), consultation.getDescription());

        emailService.sendMail(joueur.getEmailUser(), html);
    }


}












