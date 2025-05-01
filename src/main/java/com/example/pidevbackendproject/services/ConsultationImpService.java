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

        // Modifier la date
        existing.setDateConsultation(c.getDateConsultation());

        Joueurs joueur = existing.getJoueur();

        // Envoyer un email d'information sur la modification
        sendRecoveryconsultationModifierEmail(joueur, existing);

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
        String html = "<html><body style='font-family: Arial, sans-serif; color: #333; background-color: #f9f9f9; padding: 20px;'>"
                + "<div style='max-width: 600px; margin: auto; background-color: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1);'>"
                + "<img src='https://www.medisport.tn/assets/logo.png' alt='CLUBSPORT Logo' style='width: 180px; display: block; margin: 0 auto 20px;'/>"
                + "<h1 style='color: #2c3e50; text-align: center;'>CLUBSPORT - Application Médicale Sportive</h1>"
                + "<h2 style='color: #16a085;'>Bonjour " + joueur.getNameUsers() + ",</h2>"
                + "<p>Nous avons le plaisir de vous informer qu'une <strong>consultation médicale</strong> a été planifiée pour vous.</p>"
                + "<p><strong>Détails de votre rendez-vous :</strong></p>"
                + "<ul style='line-height: 1.6; padding-left: 20px;'>"
                + "<li><strong>Date :</strong> " + consultation.getDateConsultation() + "</li>"
                + "<li><strong>Programme :</strong> " + consultation.getDescription() + "</li>"
                + "</ul>"
                + "<p>Merci de noter cette date et de vous présenter à l'heure prévue.</p>"
                + "<p>Pour toute question, notre équipe reste à votre disposition.</p>"
                + "<p style='margin-top:30px;'>💪 À bientôt,<br><strong>L'équipe CLUBSPORT</strong></p>"
                + "<hr style='margin:30px 0;'>"
                + "<p style='font-size: 13px; color: #777;'>"
                + "📧 Email : <a href='mailto:CLUBSPORTFOOT@medisport.tn'>CLUBSPORTFOOT@medisport.tn</a><br>"
                + "📞 Téléphone : +216 20 345 678<br>"
                + "🌐 Site web : <a href='https://www.medisport.tn' style='color:#2c3e50;'>www.medisport.tn</a>"
                + "</p>"
                + "</div></body></html>";



        emailService.sendMail(joueur.getEmailUser(), html);
    }

    private void sendRecoveryconsultationModifierEmail(Joueurs joueur, Consultation consultation) {
        String html = "<html><body style='font-family: Arial, sans-serif; color: #333;'>"
                + "<h1 style='color: #2c3e50;'>CLUBSPORT - Application Médicale Sportive</h1>"
                + "<h2>Bonjour " + joueur.getNameUsers() + ",</h2>"
                + "<p>Nous vous informons qu'une modification a été effectuée concernant votre <strong>date de consultation médicale</strong>.</p>"
                + "<p><strong>Détails de votre nouveau rendez-vous :</strong></p>"
                + "<ul style='line-height: 1.6;'>"
                + "<li><strong>Nouvelle date de la consultation :</strong> " + consultation.getDateConsultation() + "</li>"
                + "<li><strong>Programme associé :</strong> " + consultation.getDescription() + "</li>"
                + "</ul>"
                + "<p>Nous vous prions de bien vouloir prendre note de cette nouvelle date et de vous présenter à l'heure prévue.</p>"
                + "<p>Pour toute question ou en cas de besoin d'informations supplémentaires, n'hésitez pas à nous contacter.</p>"
                + "<p style='margin-top:20px;'>💪 À bientôt,<br><strong>L'équipe CLUBSPORT</strong></p>"
                + "<hr style='margin:30px 0;'>"
                + "<p style='font-size: 13px; color: #777;'>"
                + "📧 Contact : CLUBSPORTFOOT@medisport.tn<br>"
                + "📞 Téléphone : +216 20 345 678<br>"
                + "🌐 Site web : <a href='https://www.medisport.tn' style='color:#2c3e50;'>www.medisport.tn</a>"
                + "</p>"
                + "</body></html>";



        emailService.sendMail(joueur.getEmailUser(), html);
    }


}












