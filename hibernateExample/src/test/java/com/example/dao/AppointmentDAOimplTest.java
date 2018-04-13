package com.example.dao;

import com.example.modal.Doctor;
import com.example.modal.Patient;
import com.example.modal.PatientAppointment;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;


import java.sql.Date;

import static org.mockito.Mockito.*;


public class AppointmentDAOimplTest {
//    @Autowired
    private AppointmentDAO appointmentDAO=new AppointmentDAOimpl();

    SessionFactory sessionFactory=mock(SessionFactory.class);
    protected final Session session = mock(Session.class);
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
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
        //verify(sessionFactory,atLeastOnce()).close();
        sessionFactory.getCurrentSession().saveOrUpdate(appointment);
        appointmentDAO.addPatientAppointment(appointment);


        /*when(session.save).called(savedCalled(){called=true})
        when(sessionFactory.getC).re(session)*/

    }
}
