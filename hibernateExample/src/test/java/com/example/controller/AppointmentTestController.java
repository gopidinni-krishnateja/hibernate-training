package com.example.controller;

import com.example.controller.AppointmentController;
import com.example.modal.Doctor;
import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import com.example.service.DoctorService;
import com.example.service.PatientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppointmentTestController {
    @Mock
    PatientService patientService;
    @Mock
    DoctorService doctorService;
    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();

    @InjectMocks
    AppointmentController appointmentController;
   // private MockMvc mockMvc;
    @Spy
    ModelAndView model;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
       // mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @Test
    public void saveDoctorAppointment() throws Exception {
        Integer id = 1;
        PatientAppointment appointment = new PatientAppointment();
        PatientAppointment appointment1 = new PatientAppointment();
        appointment.setId(1);
        Doctor doctor = new Doctor();
        doctor.setAppointments(Arrays.asList(appointment, appointment1));
        Patient patient = new Patient();
        patient.setAppointment(appointment);
        Patient patient1 = new Patient();
        patient1.setAppointment(appointment);
        when(patientService.getAllPatients()).thenReturn(Arrays.asList(patient, patient1));
        when(doctorService.getDoctor(45)).thenReturn(doctor);
        request.setRequestURI("/viewMyPatients");
        request.setMethod("GET");
        request.setParameter("id", "45");
        model.setViewName("viewDoctorAppointments");
        Assert.assertEquals(appointmentController.saveDoctorAppointment(request, model), model);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals(model.getModel().get("appointments"), Arrays.asList(appointment, appointment1));
        verify(patientService, atLeastOnce()).getAllPatients();
        when(patientService.getAllPatients()).thenReturn(Arrays.asList(patient, patient1));
        when(doctorService.getDoctor(45)).thenReturn(doctor);
    }

    @Test
    public void saveDoctorAppointmentWithNull() throws Exception {
        Integer id = 1;
        PatientAppointment appointment = new PatientAppointment();
        PatientAppointment appointment1 = new PatientAppointment();
        appointment.setId(1);
        Doctor doctor = new Doctor();
        List<PatientAppointment> patientAppointments=new ArrayList<PatientAppointment>();
        doctor.setAppointments(patientAppointments);
        Patient patient = new Patient();
        patient.setAppointment(appointment);
        patient.setAppointment(appointment);
        Patient patient1 = new Patient();
        when(patientService.getAllPatients()).thenReturn(Arrays.asList(patient, patient1));
        when(doctorService.getDoctor(45)).thenReturn(doctor);
        request.setRequestURI("/viewMyPatients");
        request.setMethod("GET");
        request.setParameter("id", "45");
        model.setViewName("viewDoctorAppointments");
        Assert.assertEquals(appointmentController.saveDoctorAppointment(request, model), model);
        Assert.assertEquals(200, response.getStatus());
        verify(patientService, atLeastOnce()).getAllPatients();
        when(doctorService.getDoctor(45)).thenReturn(doctor);
    }
}