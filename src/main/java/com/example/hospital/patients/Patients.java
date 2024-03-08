package com.example.hospital.patients;

import com.example.hospital.doctor.Doctor;
import com.example.hospital.medication.Medication;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Patients {
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    private Long id;
    private LocalDate dob;

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Transient
    private int age;
    private String phoneNumber;
    private String gender;
    private String roomNumber;
    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String diagnosis;
    @JsonIgnore

    @ManyToMany(mappedBy = "prescribedPatients", cascade = {CascadeType.MERGE, CascadeType.PERSIST})

    private Set<Medication> medications = new HashSet<>();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String allergies;
    private double cost;

    public Patients() {

    }

    @Override
    public String toString() {
        return "Patients{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", dob=" + dob +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", admissionDate=" + admissionDate +
                ", dischargeDate=" + dischargeDate +
                ", diagnosis='" + diagnosis + '\'' +
                ", medications=" + medications +
                ", doctor=" + doctor +
                ", allergies='" + allergies + '\'' +
                ", cost=" + cost +
                '}';
    }






    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }



    // constructor
    public Patients(String name,
                    LocalDate dob,
                    String phoneNumber,
                    String gender,
                    String roomNumber,
                    LocalDate admissionDate,
                    LocalDate dischargeDate,
                    String diagnosis,
                    Set<Medication> medications,
                    Doctor doctor,
                    String allergies,
                    double cost) {
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.roomNumber = roomNumber;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.diagnosis = diagnosis;
        this.medications = medications;
        this.doctor = doctor;
        this.allergies = allergies;
        this.cost = cost;
    }


}
