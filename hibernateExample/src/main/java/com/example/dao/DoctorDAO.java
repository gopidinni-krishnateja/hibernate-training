package com.example.dao;

import com.example.modal.Doctor;
import com.example.modal.Hospital;

import java.util.List;

public interface DoctorDAO {
    public void addDoctor(Doctor doctor);
    public void deleteDoctor(Integer id);
    public Doctor updateDoctor(Doctor doctor);
    public Doctor getDoctor(Integer doctor_id);
    public List<Doctor> getAll();
    public List<Doctor> getUnAssigned();
    public List<Doctor> searchDoctor(String firstName, Hospital hospital);
}