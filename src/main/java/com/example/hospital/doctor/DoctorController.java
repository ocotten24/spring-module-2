package com.example.hospital.doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // gets all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // createsa doctor
    @PostMapping
    public void createDoctor(@RequestBody Doctor doctor) {
        doctorService.createDoctor(doctor);
    }

    // deletes a doctor
    @DeleteMapping(path = "{id}")
    public void deleteDoctor(@PathVariable("id") Long id) {
        doctorService.deleteDoctor(id);
    }

    // updates a doctors name, gender, phonenumber, or email
    @PutMapping(path = "{id}")
    public void updateDoctor(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email) {
        doctorService.updateDoctor(id, name, gender, phoneNumber, email);
    }


    // gets a certain doctor by their id
    @GetMapping(path = "{id}")
    public Doctor getDoctorById(@PathVariable("id") Long id) {
        return doctorService.getDoctorById(id);
    }
}
