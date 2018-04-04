package com.example.dao;

import com.example.modal.Doctor;

import java.util.List;

public interface DoctorDAO {
    public void addDoctor(Doctor doctor);
    public void deleteDoctor(Integer id);
    public Doctor updateDoctor(Doctor doctor);
    public Doctor getDoctor(Integer doctor_id);
    public List<Doctor> getAll();
}