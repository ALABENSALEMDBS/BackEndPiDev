package com.example.pidevbackendproject.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;

public interface IVideoService {

    public String uploadVideo(Path root, MultipartFile file) throws IOException;

    public List<String> getAllVideos(Path root) throws IOException;

    public Resource streamVideo(Path root, String filename) throws MalformedURLException;
}
