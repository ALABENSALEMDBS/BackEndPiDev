package com.example.pidevbackendproject.services;


import com.example.pidevbackendproject.entities.LiveStream;
import com.example.pidevbackendproject.entities.Room;
import com.example.pidevbackendproject.repositories.LiveStreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiveStreamService {

    private final LiveStreamRepository liveStreamRepository;
    private final RoomService roomService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public LiveStreamService(
            LiveStreamRepository liveStreamRepository,
            RoomService roomService,
            SimpMessagingTemplate messagingTemplate) {
        this.liveStreamRepository = liveStreamRepository;
        this.roomService = roomService;
        this.messagingTemplate = messagingTemplate;
    }

    public LiveStream startLiveStream(String roomId) {
        // Activer la room
        Room room = roomService.activateRoom(roomId);

        // Créer le livestream
        LiveStream liveStream = new LiveStream(roomId);
        liveStream = liveStreamRepository.save(liveStream);

        // Notifier les clients
        messagingTemplate.convertAndSend("/topic/rooms", roomService.getActiveRooms());
        messagingTemplate.convertAndSend("/topic/room/" + roomId, "started");

        return liveStream;
    }

    public LiveStream endLiveStream(String roomId) {
        // Trouver le livestream actif pour cette room
        Optional<LiveStream> liveStreamOpt = liveStreamRepository.findByRoomId(roomId);

        if (liveStreamOpt.isPresent()) {
            LiveStream liveStream = liveStreamOpt.get();

            // Terminer le livestream
            liveStream.endStream();
            liveStream = liveStreamRepository.save(liveStream);

            // Désactiver la room
            roomService.deactivateRoom(roomId);

            // Notifier les clients
            messagingTemplate.convertAndSend("/topic/rooms", roomService.getActiveRooms());
            messagingTemplate.convertAndSend("/topic/room/" + roomId, "ended");

            return liveStream;
        }

        throw new IllegalArgumentException("No active livestream found for room ID " + roomId);
    }

    public Optional<LiveStream> findLiveStream(String id) {
        return liveStreamRepository.findById(id);
    }

    public Optional<LiveStream> findLiveStreamByRoomId(String roomId) {
        return liveStreamRepository.findByRoomId(roomId);
    }

    public List<LiveStream> getAllLiveStreams() {
        return liveStreamRepository.findAll();
    }

    public List<LiveStream> getActiveLiveStreams() {
        return liveStreamRepository.findActiveLiveStreams();
    }

    public void setRecordingPath(String roomId, String path) {
        roomService.setRecordingPath(roomId, path);
    }
}