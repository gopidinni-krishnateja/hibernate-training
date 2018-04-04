package com.example.service;

import com.example.dao.DoctorDAO;
import com.example.modal.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DoctorServiceimpl implements DoctorService {
    @Autowired
    DoctorDAO doctorDAO;
    @Override
    @Transactional
    public void addDoctor(Doctor Doctor) {
        doctorDAO.addDoctor(Doctor);
    }

    @Override
    @Transactional
    public void deleteDoctor(Integer Doctor_id) {
        doctorDAO.deleteDoctor(Doctor_id);
    }

    @Override
    public Doctor updateDoctor(Doctor Doctor) {
        return doctorDAO.updateDoctor(Doctor);
    }

    public void setDoctorDAO(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Override

    @Transactional
    public List<Doctor> getAllDoctors() {
        return doctorDAO.getAll();
    }

    @Override
    public Doctor getDoctor(Integer Doctor_id) {
        return doctorDAO.getDoctor(Doctor_id);
    }
}
