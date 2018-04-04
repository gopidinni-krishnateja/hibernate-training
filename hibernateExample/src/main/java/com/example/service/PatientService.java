package com.example.service;

import com.example.modal.Patient;

import java.util.List;

public interface PatientService {
    public void addPatient(Patient Patient);
    public void deletePatient(Integer Patient_id);
    public Patient updatePatient(Patient Patient);
    public List<Patient> getAllPatients();
    public Patient getPatient(Integer Patient_id);
}

