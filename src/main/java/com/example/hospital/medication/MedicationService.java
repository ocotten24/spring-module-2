package com.example.hospital.medication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    public Medication getMedicationById(Long id) {
        return medicationRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Medication with ID " + id + " does not exist."));
    }

    public Medication createMedication(Medication medication) {
        // Check if the medication already exists by name or any unique identifier
        Optional<Medication> existingMedication = medicationRepository.findByName(medication.getName());
        if (existingMedication.isPresent()) {
            throw new IllegalArgumentException("Medication with name " + medication.getName() + " already exists.");
        }

        // Save the medication
        return medicationRepository.save(medication);

    }

    public void deleteMedication(Long id) {
        if (!medicationRepository.existsById(id)) {
            throw new IllegalStateException("Medication with ID " + id + " does not exist.");
        }
        medicationRepository.deleteById(id);
    }
}
