package com.example.hospital.medication;

import com.example.hospital.patients.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Optional<Medication> findByName(String name);
}
