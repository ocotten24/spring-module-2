package com.example.hospital.doctor;
import com.example.hospital.patients.Patients;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gender;
    private String phoneNumber;

    public Doctor(String name, String gender, String phoneNumber, String email) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    private String email;
    @JsonIgnore

    @OneToMany(mappedBy = "doctor")
    private Set<Patients> patients = new HashSet<>();

    public Doctor() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Patients> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patients> patients) {
        this.patients = patients;
    }
}
