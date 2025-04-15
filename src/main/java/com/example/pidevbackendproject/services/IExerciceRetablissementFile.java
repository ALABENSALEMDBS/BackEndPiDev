package com.example.pidevbackendproject.services;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

public interface IExerciceRetablissementFile {
    String save(MultipartFile file);
    Resource getFile(String fileName);

}
