package com.example.pidevbackendproject;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PiDevBackEndProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiDevBackEndProjectApplication.class, args);
    }

    @Bean
    public Tesseract tesseract(){
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("eng");
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
        return tesseract;

    }

}
