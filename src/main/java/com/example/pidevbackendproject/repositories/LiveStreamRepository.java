package com.example.pidevbackendproject.repositories;

import com.example.pidevbackendproject.entities.LiveStream;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class LiveStreamRepository {
    private final Map<String, LiveStream> liveStreams = new ConcurrentHashMap<>();

    public LiveStream save(LiveStream liveStream) {
        liveStreams.put(liveStream.getId(), liveStream);
        return liveStream;
    }

    public Optional<LiveStream> findById(String id) {
        return Optional.ofNullable(liveStreams.get(id));
    }

    public Optional<LiveStream> findByRoomId(String roomId) {
        return liveStreams.values().stream()
                .filter(ls -> ls.getRoomId().equals(roomId) && ls.isActive())
                .findFirst();
    }

    public List<LiveStream> findAll() {
        return new ArrayList<>(liveStreams.values());
    }

    public List<LiveStream> findActiveLiveStreams() {
        return liveStreams.values().stream()
                .filter(LiveStream::isActive)
                .toList();
    }

    public void delete(String id) {
        liveStreams.remove(id);
    }
}