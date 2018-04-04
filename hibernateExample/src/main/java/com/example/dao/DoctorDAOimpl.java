package com.example.dao;

import com.example.modal.Doctor;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorDAOimpl implements DoctorDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addDoctor(Doctor doctor) {
        sessionFactory.getCurrentSession().saveOrUpdate(doctor);
    }

    @Override
    public void deleteDoctor(Integer doctor_id) {
        Doctor doctor = (Doctor) sessionFactory.getCurrentSession().load(Doctor.class, doctor_id);
        if(null != doctor){
            this.sessionFactory.getCurrentSession().delete(doctor_id);
        }
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        sessionFactory.getCurrentSession().update(doctor);
        return doctor;
    }

    @Override
    public Doctor getDoctor(Integer doctor_id) {
        return (Doctor) sessionFactory.getCurrentSession().get(
                Doctor.class, doctor_id);
    }

    @Override
    public List<Doctor> getAll() {
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Doctor.class);
        criteria.setMaxResults(10);
        return criteria.list();
    }
}
