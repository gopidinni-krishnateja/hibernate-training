package com.example.dao;

import com.example.modal.Hospital;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class HospitalDAOimpl implements HospitalDAO{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addHospital(Hospital hospital) {
        sessionFactory.getCurrentSession().saveOrUpdate(hospital);
    }

    @Override
    public void deleteHospital(Integer hospital_id) {
        Hospital hospital = (Hospital) sessionFactory.getCurrentSession().load(Hospital.class, hospital_id);
        if(null != hospital){
            hospital.setId(hospital_id);
            this.sessionFactory.getCurrentSession().delete(hospital);
        }
    }

    @Override
    public Hospital updateHospital(Hospital hospital) {
        sessionFactory.getCurrentSession().update(hospital);
        return hospital;
    }

    @Override
    public List<Hospital> getAllHospitals() {
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Hospital.class);
        return criteria.list();
    }

    @Override
    public Hospital getHospital(Integer hospital_id) {
        return (Hospital) sessionFactory.getCurrentSession().get(Hospital.class, hospital_id);

    }
}

