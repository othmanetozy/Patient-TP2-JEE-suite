package com.example.activite_3;

import com.example.activite_3.entities.Patient;
import com.example.activite_3.respositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Activite3Application {

    public static void main(String[] args) {
        SpringApplication.run(Activite3Application.class, args);

    }
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(new Patient(null, "othmane", new Date(), false, 14));
            patientRepository.save(new Patient(null, "hafsae", new Date(), false, 15));
            patientRepository.save(new Patient(null, "mohammed", new Date(), false, 16));
            patientRepository.save(new Patient(null, "safae", new Date(), false, 12));
            //afficher la liste des patients
            patientRepository.findAll().forEach(p -> {
                System.out.println(p.getNom());
            });
        };
    }
}

