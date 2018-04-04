package com.example.service;

import com.example.dao.HospitalDAO;
import com.example.modal.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class HospitalServiceimpl implements HospitalService {

    @Autowired
    HospitalDAO hospitalDAO;

    @Override
    @Transactional
    public void addHospital(Hospital hospital) {
        hospitalDAO.addHospital(hospital);
    }

    @Override
    @Transactional
    public void deleteHospital(Integer hospital_id) {
        hospitalDAO.deleteHospital(hospital_id);
    }

    @Override
    public Hospital updateHospital(Hospital hospital) {
        return hospitalDAO.updateHospital(hospital);
    }

    public void setHospitalDAO(HospitalDAO hospitalDAO) {
        this.hospitalDAO = hospitalDAO;
    }

    @Override
    @Transactional
    public List<Hospital> getAllHospitals() {

        return hospitalDAO.getAllHospitals();
    }

    @Override
    public Hospital getHospital(Integer hospital_id) {
        return hospitalDAO.getHospital(hospital_id);
    }
}
