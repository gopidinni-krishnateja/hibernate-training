package com.example.service;


import com.example.dao.AppointmentDAO;
import com.example.modal.Doctor;
import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class AppointmentServiceTest {
    PatientAppointment appointment=new PatientAppointment();
    @Mock
    AppointmentDAO appointmentDAO;
    @InjectMocks
    AppiontmentServiceimpl appiontmentServiceimpl;
    @Spy
    List<PatientAppointment> appointments = new ArrayList<PatientAppointment>();
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        appointments = getAppointmentList();

    }
    @Test
    public void addAppointment(){
        doNothing().when(appointmentDAO).addPatientAppointment(appointment);
        appiontmentServiceimpl.addPatientAppointment(appointment);
        verify(appointmentDAO,atLeastOnce()).addPatientAppointment(appointment);
    }
    @Test
    public void deleteAppointment(){
        int id=1;
        appointment.setId(id);
        doNothing().when(appointmentDAO).deletePatientAppointment(id);
        appiontmentServiceimpl.deletePatientAppointment(id);
        verify(appointmentDAO,atLeastOnce()).deletePatientAppointment(id);
    }
    @Test
    public void updatePatient(){
        when(appointmentDAO.updatePatientAppointment(appointment)).thenReturn(appointment);
        appiontmentServiceimpl.updatePatientAppointment(appointment);
        verify(appointmentDAO,atLeastOnce()).updatePatientAppointment(appointment);
    }
    @Test
    public void getAllAppointments(){
        when(appointmentDAO.getAll()).thenReturn(appointments);
        Assert.assertEquals(appiontmentServiceimpl.getAllPatientAppointments(),appointments);
    }
    @Test
    public void getPatientAppointment(){
        int id=1;
        appointment.setId(id);
        when(appointmentDAO.getPatientAppointment(id)).thenReturn(appointment);
        appiontmentServiceimpl.getPatientAppointment(id);
        verify(appointmentDAO,atLeastOnce()).getPatientAppointment(id);
    }
    public List<PatientAppointment> getAppointmentList(){
        PatientAppointment appointment = new PatientAppointment();
        Doctor doctor=new Doctor();
        doctor.setId(1);
        Patient patient=new Patient();
        patient.setId(1);

        appointment.setId(1);
        appointment.setPatient(patient);
        appointment.setDate(new Date(20180424).toLocalDate());
        appointment.setTime("16:20");
        appointment.setDoctor(doctor);

        PatientAppointment appointment1 = new PatientAppointment();
        Doctor doctor1=new Doctor();
        doctor1.setId(2);
        Patient patient1=new Patient();
        patient1.setId(2);
        appointment1.setId(2);
        appointment1.setPatient(patient1);
        appointment1.setDate(new Date(20180424).toLocalDate());
        appointment1.setTime("16:20");
        appointment1.setDoctor(doctor1);
       appointments.add(appointment);
       appointments.add(appointment1);
        return appointments;
    }
}
