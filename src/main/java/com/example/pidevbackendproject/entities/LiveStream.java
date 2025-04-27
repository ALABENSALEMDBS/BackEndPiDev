package com.example.pidevbackendproject.entities;

import java.time.LocalDateTime;

public class LiveStream {
    private String id;
    private String roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean active;

    public LiveStream() {
        this.startTime = LocalDateTime.now();
        this.active = true;
    }

    public LiveStream(String roomId) {
        this();
        this.roomId = roomId;
        this.id = roomId; // Pour simplifier, on utilise le même ID que la room
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void endStream() {
        this.active = false;
        this.endTime = LocalDateTime.now();
    }
}