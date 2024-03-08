package com.example.hospital.medication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MedicationConfig {

    @Bean
    CommandLineRunner medicationCommandLineRunner(MedicationRepository medicationRepository) {
        return args -> {
            Medication medication1 = new Medication(
                    "Aspirin",
                    "200mg",
                    "Twice Daily",
                    "Take with water"
            );
            medicationRepository.save(medication1);
        };
    }
}
