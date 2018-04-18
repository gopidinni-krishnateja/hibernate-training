package com.example.controller;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import com.example.service.AppointmentService;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;


import static org.mockito.Mockito.when;

public class PatientTestController {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Mock
    DoctorService doctorService;
    @Mock
    HospitalService hospitalService;
    @Mock
    PatientService patientService;
    @InjectMocks
    WebDataBinder binder;
    @Mock
    AppointmentService appointmentService;
    @Spy
    ModelAndView model;
    @InjectMocks
    PatientController patientController;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
    }
    @Test
    public void fixAppointmentTest(){
        Doctor doctor=new Doctor();
        Doctor doctor1=new Doctor();
        Doctor doctor2=new Doctor();

        request.setRequestURI("/addPatient");
        when(doctorService.getAllDoctors()).thenReturn(Arrays.asList(doctor,doctor1,doctor2));
        request.setAttribute("doctor",doctor1);
        request.setRequestURI("/saveDoctor");
        request.setMethod("POST");
        model.setViewName("/patientForm");
        Assert.assertEquals(patientController.fixAppointment(model),model);
        Assert.assertEquals(200, response.getStatus());

    }
    @Test
    public void addPatientTest(){
        PatientAppointment appointment=new PatientAppointment();
        Patient patient=new Patient();
        Hospital hospital=new Hospital();
        Hospital hospital1=new Hospital();
        Hospital hospital2=new Hospital();
        patient.setFirstName("Krishna");
        patient.setLastname("Teja");
        patient.setAge(24);
        patient.setId(1);
        patient.setGender("MALE");
        appointment.setId(1);
        appointment.setDate(new Date(20180424).toLocalDate());
        appointment.setTime("15:20");
        patient.setAppointment(appointment);
        when(hospitalService.getAllHospitals()).thenReturn(Arrays.asList(hospital,hospital1,hospital2));
        model.addObject("hospitals",Arrays.asList(hospital,hospital1,hospital2));
        model.setViewName("/index");
        Assert.assertEquals(patientController.addPatient((Patient) patient,model,request),model);
    }
    @Test
    public void addPatientTestElse(){
        PatientAppointment appointment=new PatientAppointment();
        Patient patient=new Patient();
        Hospital hospital=new Hospital();
        Hospital hospital1=new Hospital();
        Hospital hospital2=new Hospital();
        patient.setFirstName("Krishna");
        patient.setLastname("Teja");
        patient.setAge(24);
        patient.setGender("MALE");
        appointment.setId(1);
        appointment.setDate(new Date(20180424).toLocalDate());
        appointment.setTime("15:20");
        patient.setAppointment(appointment);
        when(hospitalService.getAllHospitals()).thenReturn(Arrays.asList(hospital,hospital1,hospital2));
        model.addObject("hospitals",Arrays.asList(hospital,hospital1,hospital2));
        model.setViewName("/index");
        Assert.assertEquals(patientController.addPatient((Patient) patient,model,request),model);
    }
    @Test
    public void viewPatients(){
        Patient patient=new Patient();
        Patient patient1=new Patient();
        Patient patient2=new Patient();
        when(patientService.getAllPatients()).thenReturn(Arrays.asList(patient,patient1,patient2));
        model.addObject("patients",Arrays.asList(patient,patient1,patient2));
        model.setViewName("viewPatients");
        Assert.assertEquals(patientController.viewPatients(model,request),model);
    }
    @Test
    public void editPatient(){
        Patient patient=new Patient();
        patient.setId(2);
        when(patientService.getPatient(2)).thenReturn(patient);
        Doctor doctor=new Doctor();
        Doctor doctor1=new Doctor();
        Doctor doctor2=new Doctor();
        when(doctorService.getAllDoctors()).thenReturn(Arrays.asList(doctor,doctor1,doctor2));
        model.addObject("doctors",Arrays.asList(doctor,doctor1,doctor2));
        request.setMethod("editPatient");
        request.setMethod("GET");
        request.setParameter("id","2");
        Assert.assertEquals(patientController.editPatient(model,request),model);
    }
    @Test
    public void deletePatientTest(){
        Hospital hospital=new Hospital();
        Hospital hospital1=new Hospital();
       Patient patient=new Patient();
       patient.setId(1);
        when(hospitalService.getAllHospitals()).thenReturn(Arrays.asList(hospital,hospital,hospital1));
        request.setParameter("id","1");
        request.setMethod("GET");
        model.setViewName("/index");
        model.addObject("hospitals",Arrays.asList(hospital,hospital,hospital1));
        Assert.assertEquals(patientController.deletePatient(model,request), model);
    }
    @Test
    public void testInitBinder(){
        patientController.initBinder(binder);
    }


}
