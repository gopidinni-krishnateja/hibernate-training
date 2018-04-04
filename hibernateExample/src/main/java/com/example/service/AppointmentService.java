package com.example.service;

import com.example.modal.PatientAppointment;

import java.util.List;

public interface AppointmentService {
    public void addPatientAppointment(PatientAppointment PatientAppointment);
    public void deletePatientAppointment(Integer PatientAppointment_id);
    public PatientAppointment updatePatientAppointment(PatientAppointment PatientAppointment);
    public List<PatientAppointment> getAllPatientAppointments();
    public PatientAppointment getPatientAppointment(Integer PatientAppointment_id);
}
