package com.example.dao;

import com.example.dao.AppointmentDAO;
import com.example.modal.Doctor;
import com.example.modal.PatientAppointment;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AppointmentDAOimpl implements AppointmentDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addPatientAppointment(PatientAppointment patientAppointment) {
        sessionFactory.getCurrentSession().save(patientAppointment);
    }

    @Override
    public PatientAppointment updatePatientAppointment(PatientAppointment PatientAppointment) {
        sessionFactory.getCurrentSession().update(PatientAppointment);
        return PatientAppointment;
    }

    @Override
    public PatientAppointment getPatientAppointment(Integer PatientAppointment_id) {
        return (PatientAppointment) sessionFactory.getCurrentSession().get(
                PatientAppointment.class, PatientAppointment_id);

    }

    @Override
    public List<PatientAppointment> getAll() {
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(PatientAppointment.class);
        return criteria.list();
    }

    @Override
    public void deletePatientAppointment(Integer PatientAppointment_id) {
        PatientAppointment patientAppointment = (PatientAppointment) sessionFactory.getCurrentSession().load(PatientAppointment.class, PatientAppointment_id);
        if(null != patientAppointment){
            this.sessionFactory.getCurrentSession().delete(PatientAppointment_id);
        }
    }
}
