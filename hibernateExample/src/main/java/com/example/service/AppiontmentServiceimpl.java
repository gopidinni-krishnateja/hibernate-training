package com.example.service;

import com.example.dao.AppointmentDAO;
import com.example.modal.PatientAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class AppiontmentServiceimpl implements AppointmentService{

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Override
    @Transactional
    public void addPatientAppointment(PatientAppointment PatientAppointment) {
        appointmentDAO.addPatientAppointment(PatientAppointment);
    }

    @Override
    @Transactional
    public void deletePatientAppointment(Integer PatientAppointment_id) {
        appointmentDAO.deletePatientAppointment(PatientAppointment_id);
    }

    @Override
    public PatientAppointment updatePatientAppointment(PatientAppointment PatientAppointment) {
        return appointmentDAO.updatePatientAppointment(PatientAppointment);
    }

    @Override
    @Transactional
    public List<PatientAppointment> getAllPatientAppointments() {
        return appointmentDAO.getAll();
    }

    @Override
    public PatientAppointment getPatientAppointment(Integer PatientAppointment_id) {
        return appointmentDAO.getPatientAppointment(PatientAppointment_id);
    }
}
