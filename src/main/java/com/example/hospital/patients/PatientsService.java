package com.example.hospital.patients;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientsService {

    private final PatientsRepository patientsRepository;

    @Autowired
    public PatientsService(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    public List<Patients> getPatients() {
        return patientsRepository.findAll();
    }

    public void addNewPatient(Patients patients) {
        Optional<Patients> patientsOptional = patientsRepository.findPatientsByPhoneNumber(patients.getPhoneNumber());
        if (patientsOptional.isPresent()) {
            throw new IllegalStateException("Phone number already taken.");
        }
        patientsRepository.save(patients);
    }

    public void deletePatients(Long patientsId) {
        patientsRepository.findById(patientsId);
        boolean exists = patientsRepository.existsById(patientsId);
        if (!exists) {
            throw new IllegalStateException(
                    "Patient with ID" + patientsId + " does not exist.");
        }
        patientsRepository.deleteById(patientsId);
    }


    @Transactional
    public void updatePatients(Long patientsId,
                               String name,
                               String phoneNumber,
                               String roomNumber) {
        Patients patients = patientsRepository.findById(patientsId)
                .orElseThrow(() -> new IllegalStateException(
                        "Patient with ID" + patientsId + " does not exist."));

        if (name != null &&
                !name.isEmpty() &&
                !Objects.equals(patients.getName(), name)) {
            patients.setName(name);
        }

        if (phoneNumber != null &&
                !phoneNumber.isEmpty() &&
                !Objects.equals(patients.getPhoneNumber(), phoneNumber)) {
            Optional<Patients> patientsOptional = patientsRepository.findPatientsByPhoneNumber(patients.getPhoneNumber());
            if (patientsOptional.isPresent()) {
                throw new IllegalStateException("Phone number already taken.");
            }
            patients.setPhoneNumber(phoneNumber);
        }

        if (roomNumber != null &&
                !roomNumber.isEmpty() &&
                !Objects.equals(patients.getRoomNumber(), roomNumber)) {
            Optional<Patients> patientsOptional = patientsRepository.findPatientsByRoomNumber(roomNumber);
            if (patientsOptional.isPresent()) {
                throw new IllegalStateException("Room number already taken.");
            }
            patients.setRoomNumber(roomNumber);
        }
        patientsRepository.save(patients);
    }

    public Patients getPatientsById(Long patientsId) {
        return patientsRepository.findById(patientsId)
                .orElseThrow(() -> new IllegalStateException(
                        "Patient with ID " + patientsId + " does not exist."));
    }

    public void updatePatient(Patients patient) {
        patientsRepository.save(patient);
    }
}