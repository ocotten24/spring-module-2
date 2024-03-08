package com.example.hospital.doctor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DoctorConfig {

    @Bean
    CommandLineRunner doctorCommandLineRunner(DoctorRepository repository) {
        return args -> {
            Doctor doctor1 = new Doctor("Dr. Smith", "Male", "662-238-9330", "dr.smith@legithospital.com");
            Doctor doctor2 = new Doctor("Dr. Johnson", "Female", "662-393-2993", "dr.johnson@legithospital.com");

            repository.saveAll(List.of(doctor1, doctor2));
        };
    }
}