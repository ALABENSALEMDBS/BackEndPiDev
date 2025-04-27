package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RoomRepository {
    private final Map<String, Room> rooms = new ConcurrentHashMap<>();

    public Room save(Room room) {
        rooms.put(room.getId(), room);
        return room;
    }

    public Optional<Room> findById(String id) {
        return Optional.ofNullable(rooms.get(id));
    }

    public List<Room> findAll() {
        return new ArrayList<>(rooms.values());
    }

    public List<Room> findActiveRooms() {
        return rooms.values().stream()
                .filter(Room::isActive)
                .toList();
    }

    public void delete(String id) {
        rooms.remove(id);
    }
}