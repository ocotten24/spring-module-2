package com.example.hospital.medication;

import com.example.hospital.patients.Patients;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToMany
    @JoinTable(
            name = "patient_prescribed",
            joinColumns = @JoinColumn(name = "medication_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private Set<Patients> prescribedPatients = new HashSet<>();

    public Medication(String name, String dosage, String frequency, String instructions) {
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.instructions = instructions;
    }

    private String name;
    private String dosage;
    private String frequency;
    private String instructions;

    public Medication() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Patients> getPrescribedPatients() {
        return prescribedPatients;
    }

    public void setPrescribedPatients(Set<Patients> prescribedPatients) {
        this.prescribedPatients = prescribedPatients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
