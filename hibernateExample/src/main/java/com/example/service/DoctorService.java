package com.example.service;

import com.example.modal.Doctor;

import java.util.List;

public interface DoctorService {
    public void addDoctor(Doctor Doctor);
    public void deleteDoctor(Integer Doctor_id);
    public Doctor updateDoctor(Doctor Doctor);
    public List<Doctor> getAllDoctors();
    public Doctor getDoctor(Integer Doctor_id);
}