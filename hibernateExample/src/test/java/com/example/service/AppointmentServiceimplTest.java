package com.example.service;

import com.example.dao.AppointmentDAO;
import com.example.dao.AppointmentDAOimpl;
import com.example.modal.Doctor;
import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.Arrays;

import static org.mockito.Mockito.when;

public class AppointmentServiceimplTest {

    @Autowired
    AppointmentService appointmentService=new AppiontmentServiceimpl();
    AppointmentDAO appointmentDAO=new AppointmentDAOimpl();
    @Mock
    SessionFactory sessionFactory;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @Test
    public void addPatientAppointmentTest(){
        PatientAppointment appointment=new PatientAppointment();
        appointment.setId(10);
        appointment.setDate(new Date(20180424).toLocalDate());
        appointment.setTime("15:60");
        Doctor doctor=new Doctor();
        doctor.setId(10);
        Patient patient=new Patient();
        patient.setId(12);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        when(appointmentDAO.getAll()).thenReturn(Arrays.asList(appointment,appointment));
        appointmentService.getAllPatientAppointments();
    }
}
