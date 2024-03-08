package com.example.hospital.doctor;

import com.example.hospital.medication.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
