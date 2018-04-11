package com.example.controller;

import com.example.controller.AppointmentController;
import com.example.modal.Patient;
import com.example.service.PatientService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

public class AppointmentTestController {
    @Mock
    PatientService patientService;
    @InjectMocks
    AppointmentController appointmentController;
    private MockMvc mockMvc;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc=MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @Test
    public void saveDoctorAppointment() throws Exception{
        Integer id=1;
        List<Patient> patients=new ArrayList<>();
        patients.add(new Patient());
        patients.add(new Patient());
        when(patientService.getAllPatients()).thenReturn((List) patients);
        mockMvc.perform(get("/viewMyPatients/1"))
                .andExpect(status().is(404))
                .andExpect(view().name("viewDoctorAppointments"))
                .andExpect(forwardedUrl("WEB-INF/jsp/viewDoctorAppointments.jsp"))
                .andExpect(model().attribute("patients",hasSize(2)));

    }


}