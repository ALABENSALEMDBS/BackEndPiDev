package com.example.pidevbackendproject.services;


import com.example.pidevbackendproject.entities.Room;
import com.example.pidevbackendproject.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room createRoom(String name) {
        Room room = new Room(name);
        return roomRepository.save(room);
    }

    public Optional<Room> findRoom(String id) {
        return roomRepository.findById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> getActiveRooms() {
        return roomRepository.findActiveRooms();
    }

    public Room activateRoom(String id) {
        Optional<Room> roomOpt = roomRepository.findById(id);
        if (roomOpt.isPresent()) {
            Room room = roomOpt.get();
            room.setActive(true);
            return roomRepository.save(room);
        }
        throw new IllegalArgumentException("Room with ID " + id + " not found");
    }

    public Room deactivateRoom(String id) {
        Optional<Room> roomOpt = roomRepository.findById(id);
        if (roomOpt.isPresent()) {
            Room room = roomOpt.get();
            room.setActive(false);
            return roomRepository.save(room);
        }
        throw new IllegalArgumentException("Room with ID " + id + " not found");
    }

    public Room setRecordingPath(String id, String path) {
        Optional<Room> roomOpt = roomRepository.findById(id);
        if (roomOpt.isPresent()) {
            Room room = roomOpt.get();
            room.setRecordingPath(path);
            return roomRepository.save(room);
        }
        throw new IllegalArgumentException("Room with ID " + id + " not found");
    }
}