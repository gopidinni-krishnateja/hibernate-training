package com.example.controller;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
import com.example.service.DoctorService;
import com.example.service.HospitalService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class DoctorTestController {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Mock
    DoctorService doctorService;
    @Mock
    HospitalService hospitalService;
    @Spy
    ModelAndView model;

    ModelAttribute attribute;
    @InjectMocks
    DoctorController doctorController;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
    }
    @Test
    public void addNewDoctorTest(){
        request.setRequestURI("/newDoctor");
        request.setMethod("GET");
        model.setViewName("addDoctor");
        Assert.assertEquals(doctorController.newDoctor(model,request), model);
    }
    @Test
    public void addDoctorTest(){
        Hospital hospital=new Hospital();
        Doctor doctor1=new Doctor();
        doctor1.setFirstName("Krishna");
        doctor1.setLastName("Krishna");
        doctor1.setType("ENT");
        Doctor doctor=new Doctor();
        doctor.setId(12);
        hospital.setId(10);
        hospital.setName("CARE");
        hospital.setCityName("Hyderabad");
        doctor.setHospital(hospital);
        doctor1.setHospital(hospital);
        hospital.setDoctors(Arrays.asList(doctor1,doctor));

        when(hospitalService.getHospital(12)).thenReturn(doctor.getHospital());
        when(hospitalService.getAllHospitals()).thenReturn(Arrays.asList(new Hospital(),new Hospital()));
        request.setAttribute("doctor",doctor1);
        request.setRequestURI("/saveDoctor");
        request.setMethod("POST");
        model.setViewName("index");
        Assert.assertEquals(doctorController.addDoctor((Doctor) attribute, model,request), model);
        Assert.assertEquals(200, response.getStatus());
    }

}
