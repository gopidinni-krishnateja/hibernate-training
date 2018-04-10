package com.example.dao;

import com.example.modal.Hospital;
import com.example.modal.Patient;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static org.springframework.orm.hibernate3.SessionFactoryUtils.getSession;

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
        Criteria c = sessionFactory.getCurrentSession().createCriteria(Patient.class, "pa");
        c.createAlias("pa.appointment", "ap");
        c.createAlias("ap.doctor", "doc");
        c.createAlias("doc.hospital", "hos");
        ProjectionList proList = Projections.projectionList();
        proList.add(Projections.alias(Projections.property("pa.id"),"PatientId"));
        proList.add(Projections.alias(Projections.property("pa.firstName"),"PatientFirstName"));
        proList.add(Projections.alias(Projections.property("pa.lastname"),"PatientLastName"));
        proList.add(Projections.alias(Projections.property("ap.date"),"date"));
        proList.add(Projections.alias(Projections.property("ap.time"),"time"));
        proList.add(Projections.alias(Projections.property("doc.firstName"),"DoctorFirstName"));
        proList.add(Projections.alias(Projections.property("doc.lastName"),"DoctorLastName"));
        proList.add(Projections.alias(Projections.property("hos.name"),"HospitalName"));
        c.setProjection(proList);
        c.addOrder(org.hibernate.criterion.Order.asc("date"));
        c.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return c.list();
    }

    @Override
    public Patient getPatient(Integer Patient_id) {
        return (Patient) sessionFactory.getCurrentSession().get(Patient.class, Patient_id);

    }
}

