package com.example.pidevbackendproject.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebRTCController {

    private static final Logger logger = LoggerFactory.getLogger(WebRTCController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/webrtc/{roomId}/offer")
    public void handleOffer(@DestinationVariable String roomId, String offerSdp) {
        System.out.println("Received offer for room: " + roomId + ", length: " + offerSdp.length());
        // Transmettre l'offre à tous les spectateurs de cette room
        messagingTemplate.convertAndSend("/topic/webrtc/" + roomId + "/offer", offerSdp);
        System.out.println("Offer sent to viewers for room: " + roomId);
    }

    @MessageMapping("/webrtc/{roomId}/answer")
    public void handleAnswer(@DestinationVariable String roomId, String answerSdp) {
        System.out.println("Received answer for room: " + roomId + ", length: " + answerSdp.length());
        // Transmettre la réponse au diffuseur
        messagingTemplate.convertAndSend("/topic/webrtc/" + roomId + "/answer", answerSdp);
        System.out.println("Answer sent to broadcaster for room: " + roomId);
    }

    @MessageMapping("/webrtc/{roomId}/ice-candidate")
    public void handleIceCandidate(@DestinationVariable String roomId, String iceCandidate) {
        System.out.println("Received ICE candidate for room: " + roomId);
        // Transmettre le candidat ICE aux autres participants
        messagingTemplate.convertAndSend("/topic/webrtc/" + roomId + "/ice-candidate", iceCandidate);
        System.out.println("ICE candidate sent for room: " + roomId);
    }

    @MessageMapping("/webrtc/{roomId}/connect")
    public void handleConnectionRequest(@DestinationVariable String roomId, String request) {
        System.out.println("Received connection request for room: " + roomId + " - " + request);
        // Relayer la demande au broadcaster
        messagingTemplate.convertAndSend("/topic/webrtc/" + roomId + "/connect-request", request);
    }
}