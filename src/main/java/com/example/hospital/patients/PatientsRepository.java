package com.example.hospital.patients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface PatientsRepository extends JpaRepository<Patients, Long> {

Optional<Patients> findPatientsByPhoneNumber(String phoneNumber);
Optional<Patients> findPatientsByRoomNumber(String roomNumber);
}

