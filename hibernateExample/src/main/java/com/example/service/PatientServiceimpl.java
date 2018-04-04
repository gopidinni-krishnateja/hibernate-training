package com.example.service;

import com.example.dao.PatientDAO;
import com.example.modal.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class PatientServiceimpl  implements PatientService {
    @Autowired
    PatientDAO patientDAO;
    @Override
    @Transactional
    public void addPatient(Patient Patient) {
        patientDAO.addPatient(Patient);
    }

    public PatientDAO getPatientDAO() {
        return patientDAO;
    }

    public void setPatientDAO(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Override
    @Transactional
    public void deletePatient(Integer Patient_id) {
        patientDAO.deletePatient(Patient_id);
    }

    @Override
    public Patient updatePatient(Patient Patient) {
        return patientDAO.updatePatient(Patient);
    }

    @Override
    @Transactional
    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    @Override
    public Patient getPatient(Integer Patient_id) {
        return patientDAO.getPatient(Patient_id);
    }
}
