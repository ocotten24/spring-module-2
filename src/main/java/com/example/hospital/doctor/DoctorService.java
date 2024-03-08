package com.example.hospital.doctor;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hospital.doctor.Doctor;
import com.example.hospital.doctor.DoctorRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Doctor with ID " + id + " does not exist."));
    }

    public void createDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }


    @Transactional
    public void updateDoctor(Long id, String name, String gender, String phoneNumber, String email) {
        Doctor doctor = getDoctorById(id);
        if (name != null && !name.isEmpty() && !Objects.equals(doctor.getName(), name)) {
            doctor.setName(name);
        }
        if (gender != null && !gender.isEmpty() && !Objects.equals(doctor.getGender(), gender)) {
            doctor.setGender(gender);
        }
        if (phoneNumber != null && !phoneNumber.isEmpty() && !Objects.equals(doctor.getPhoneNumber(), phoneNumber)) {
            doctor.setPhoneNumber(phoneNumber);
        }
        if (email != null && !email.isEmpty() && !Objects.equals(doctor.getEmail(), email)) {
            doctor.setEmail(email);
        }
        doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new IllegalStateException("Doctor with ID " + id + " does not exist.");
        }
        doctorRepository.deleteById(id);
    }
}
