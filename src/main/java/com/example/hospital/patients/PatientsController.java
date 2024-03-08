package com.example.hospital.patients;
import com.example.hospital.doctor.*;
import com.example.hospital.medication.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/patients")
public class PatientsController {

    private final PatientsService patientsService;
    private final DoctorService doctorService;

    @Autowired
    public PatientsController(PatientsService patientsService,  DoctorService doctorService) {
        this.patientsService = patientsService;
        this.doctorService = doctorService;
    }


    // returns all patients
    @GetMapping
    public List<Patients> getPatients() {
        return patientsService.getPatients();
    }


    // adds a new patient
    @PostMapping
    public void registerNewPatient(@RequestBody Patients patients) {
        patientsService.addNewPatient(patients);
    }


    // deletes a patient
    @DeleteMapping("/{patientId}")
    public void deletePatients(@PathVariable Long patientId) {
        patientsService.deletePatients(patientId);
    }



    // updates the name phonenumber or roomnumber of a patient
    @PutMapping("/{patientId}")
    public void updatePatients(
            @PathVariable Long patientId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String roomNumber) {
        patientsService.updatePatients(patientId, name, phoneNumber, roomNumber);
    }


    // gets patient by id
    @GetMapping("/{patientId}")
    public Patients getPatient(@PathVariable Long patientId) {
        return patientsService.getPatientsById(patientId);
    }


    // assigns a doctor to a patient
    @PutMapping("/{patientId}/doctor/{doctorId}")
    public void assignDoctorToPatient(
            @PathVariable Long patientId,
            @PathVariable Long doctorId) {
        Patients patient = patientsService.getPatientsById(patientId);
        Doctor doctor = doctorService.getDoctorById(doctorId);
        patient.setDoctor(doctor);
        patientsService.updatePatient(patient);
    }
}

