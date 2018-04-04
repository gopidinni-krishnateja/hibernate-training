package com.example.service;

import com.example.modal.Hospital;

import java.util.List;

public interface HospitalService {
    public void addHospital(Hospital hospital);
    public void deleteHospital(Integer hospital_id);
    public Hospital updateHospital(Hospital hospital);
    public List<Hospital> getAllHospitals();
    public Hospital getHospital(Integer hospital_id);
}
