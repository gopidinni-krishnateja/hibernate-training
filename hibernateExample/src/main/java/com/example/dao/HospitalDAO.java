package com.example.dao;

import com.example.modal.Hospital;

import java.util.List;

public interface HospitalDAO {
    public void addHospital(Hospital hospital);
    public void deleteHospital(Integer hospital_id);
    public Hospital updateHospital(Hospital hospital);
    public List<Hospital> getAllHospitals();
    public Hospital getHospital(Integer hospital_id);
}