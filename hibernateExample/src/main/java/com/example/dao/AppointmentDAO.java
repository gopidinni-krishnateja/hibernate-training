package com.example.dao;

import com.example.modal.PatientAppointment;

import java.util.List;

public interface AppointmentDAO {

    public void addPatientAppointment(PatientAppointment PatientAppointment);
    public PatientAppointment updatePatientAppointment(PatientAppointment PatientAppointment);
    public PatientAppointment getPatientAppointment(Integer PatientAppointment_id);
    public List<PatientAppointment> getAll();
    public void deletePatientAppointment(Integer PatientAppointment_id);
}
