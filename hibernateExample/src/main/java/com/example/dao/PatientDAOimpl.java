package com.example.dao;

import com.example.modal.Hospital;
import com.example.modal.Patient;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PatientDAOimpl implements  PatientDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addPatient(Patient patient) {
        System.out.println("AppointmentData->  "+patient.getAppointment());
        sessionFactory.getCurrentSession().saveOrUpdate(patient);
    }

    @Override
    public void deletePatient(Integer Patient_id) {
        Patient patient = (Patient) sessionFactory.getCurrentSession().load(Patient.class, Patient_id);
        if(null != patient){
            this.sessionFactory.getCurrentSession().delete(patient);
        }
    }

    @Override
    public Patient updatePatient(Patient Patient) {
        sessionFactory.getCurrentSession().update(Patient);
        return Patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Patient.class);

        return criteria.list();
    }

    @Override
    public Patient getPatient(Integer Patient_id) {
        return (Patient) sessionFactory.getCurrentSession().get(Patient.class, Patient_id);

    }
}

