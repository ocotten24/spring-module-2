package com.example.hospital.medication;

import com.example.hospital.patients.Patients;
import com.example.hospital.patients.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/medications")
public class MedicationController {

    private final MedicationService medicationService;
    private final PatientsService patientsService;

    @Autowired
    public MedicationController(MedicationService medicationService, PatientsService patientsService) {
        this.medicationService = medicationService;
        this.patientsService = patientsService;
    }


    // gets all medications
    @GetMapping
    public List<Medication> getAllMedications() {
        return medicationService.getAllMedications();
    }


    // get certain medicines by id
    @GetMapping("/{id}")
    public Medication getMedicationById(@PathVariable Long id) {
        return medicationService.getMedicationById(id);
    }

    // create a new medication
    @PostMapping
    public Medication createMedication(@RequestBody Medication medication) {
        return medicationService.createMedication(medication);
    }

    // deleting medications by id
    @DeleteMapping("/{id}")
    public void deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
    }


    // assigns medication to patients
    @PutMapping("/{medicationId}/assign-to-patient/{patientId}")
    public void assignMedicationToPatient(
            @PathVariable Long medicationId,
            @PathVariable Long patientId) {
        Medication medication = medicationService.getMedicationById(medicationId);
        Patients patient = patientsService.getPatientsById(patientId);

        patient.getMedications().add(medication);
        medication.getPrescribedPatients().add(patient);

        patientsService.updatePatient(patient);
    }


    // removes medications from patients
    @PutMapping("/{medicationId}/remove-from-patient/{patientId}")
    public void removeMedicationFromPatient(
            @PathVariable Long medicationId,
            @PathVariable Long patientId) {
        Medication medication = medicationService.getMedicationById(medicationId);
        Patients patient = patientsService.getPatientsById(patientId);

        patient.getMedications().remove(medication);
        medication.getPrescribedPatients().remove(patient);

        patientsService.updatePatient(patient);

    }




}
