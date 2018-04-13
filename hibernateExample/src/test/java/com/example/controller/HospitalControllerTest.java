package com.example.controller;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
import com.example.service.DoctorService;
import com.example.service.HospitalService;
import com.example.service.PatientService;
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

import java.util.Arrays;

import static org.mockito.Mockito.when;

public class HospitalControllerTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Mock
    DoctorService doctorService;
    @Mock
    HospitalService hospitalService;
    @Mock
    PatientService patientService;
    @Spy
    ModelAndView model;

    @InjectMocks
    HospitalController hospitalController;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
    }
    @Test
    public void homePageTest(){
        Hospital hospital=new Hospital();
        hospital.setId(10);
        hospital.setName("CARE");
        hospital.setCityName("Hyderabad");
        when(hospitalService.getAllHospitals()).thenReturn(Arrays.asList(hospital,hospital,hospital));
        model.addObject("hospitals",Arrays.asList(hospital,hospital,hospital));
        request.setRequestURI("/index");
        request.setMethod("GET");
        model.setViewName("index");
        Assert.assertEquals(hospitalController.homePage(), model);

    }
    @Test
    public void newHospitalTest(){
        Doctor doctor=new Doctor();
        Doctor doctor1=new Doctor();
        Hospital hospital=new Hospital();
        hospital.setId(1);
        when(doctorService.getUnAssignedDoctors()).thenReturn(Arrays.asList(doctor,doctor1));
        request.setMethod("GET");
        model.setViewName("hospital");
        Assert.assertEquals(hospitalController.newHospital(model), model);
    }
    @Test
    public void saveHospitalTest(){
        Doctor doctor=new Doctor();
        Doctor doctor1=new Doctor();
        Doctor doctor2=new Doctor();
        doctor.setId(12);
        doctor1.setId(13);
        doctor2.setId(16);
        Hospital hospital=new Hospital();
        Hospital hospital1=new Hospital();
        Hospital hospital2=new Hospital();
        String[] Array={"12","13","16"};
        when(doctorService.getDoctor(12)).thenReturn(doctor);
        when(doctorService.getDoctor(13)).thenReturn(doctor1);
        when(doctorService.getDoctor(16)).thenReturn(doctor2);
        when(hospitalService.getAllHospitals()).thenReturn(Arrays.asList(hospital,hospital1,hospital2));
        request.setParameter("doctors",Array);
        request.setParameter("name","CARE");
        request.setParameter("cityName","Hyderabad");
        request.setMethod("POST");
        model.setViewName("viewhospitals");
        model.addObject("hospitals",Arrays.asList(hospital,hospital1,hospital2));
        Assert.assertEquals(hospitalController.saveHospital(request), model);
    }
    @Test
    public void saveHospitalDoctorsTest(){
        Hospital hospital=new Hospital();
        Hospital hospital1=new Hospital();
        Hospital hospital2=new Hospital();
        hospital.setId(12);
        hospital1.setId(13);
        hospital2.setId(14);
        Doctor doctor=new Doctor();
        Doctor doctor1=new Doctor();
        Doctor doctor2=new Doctor();
        doctor.setId(12);
        doctor1.setId(13);
        doctor2.setId(16);
        doctor.setHospital(hospital);
        doctor1.setHospital(hospital1);
        doctor2.setHospital(hospital2);
        String[] Array={"12","13","16"};
        when(doctorService.getDoctor(12)).thenReturn(doctor);
        when(doctorService.getDoctor(13)).thenReturn(doctor1);
        when(doctorService.getDoctor(16)).thenReturn(doctor2);
        when(hospitalService.getHospital(12)).thenReturn(hospital);
        when(hospitalService.getAllHospitals()).thenReturn(Arrays.asList(hospital,hospital1,hospital2));
        request.setParameter("doctors",Array);
        request.setParameter("hospitalId","12");
        request.setParameter("name","CARE");
        request.setParameter("cityName","Hyderabad");
        request.setMethod("POST");
        model.setViewName("viewhospitals");
        model.addObject("hospitals",Arrays.asList(hospital,hospital1,hospital2));
        Assert.assertEquals(hospitalController.saveHospitalDoctors(request), model);
    }
    @Test
    public void viewHospitalsTest(){
        Hospital hospital=new Hospital();
        Hospital hospital1=new Hospital();
        Hospital hospital2=new Hospital();
        when(hospitalService.getAllHospitals()).thenReturn(Arrays.asList(hospital,hospital1,hospital2));
        request.setRequestURI("/viewhospitals");
        request.setMethod("GET");
        model.setViewName("viewhospitals");
        model.addObject("hospitals",Arrays.asList(hospital,hospital1,hospital2));
        Assert.assertEquals(hospitalController.viewHospital(model), model);
    }
    @Test
    public void deleteDoctorTest(){
        Hospital hospital=new Hospital();
        Hospital hospital1=new Hospital();
        when(hospitalService.getAllHospitals()).thenReturn(Arrays.asList(hospital,hospital,hospital1));
        request.setParameter("id","1");
        request.setMethod("GET");
        model.setViewName("/index");
        model.addObject("hospitals",Arrays.asList(hospital,hospital,hospital1));
        Assert.assertEquals(hospitalController.deleteDoctor(model,request),model);
    }
}
