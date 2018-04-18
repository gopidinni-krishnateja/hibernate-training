package com.example.controller;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
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
    @Mock
    PatientService patientService;
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
        List<Hospital> hospitals=new ArrayList<Hospital>();
        Hospital hospital=new Hospital();
        hospital.setId(10);
        hospital.setName("CARE");
        hospital.setCityName("Hyderabad");
        Doctor doctor1=new Doctor();
        doctor1.setId(1);
        doctor1.setFirstName("Krishna");
        doctor1.setLastName("Krishna");
        doctor1.setType("ENT");
        doctor1.setHospital(hospital);
        Doctor doctor=new Doctor();
        doctor.setId(12);
        doctor.setHospital(hospital);
        hospital.setDoctors(Arrays.asList(doctor1,doctor));
        when(hospitalService.getHospital(doctor1.getHospital().getId())).thenReturn(doctor1.getHospital());
        when(hospitalService.getAllHospitals()).thenReturn(Arrays.asList(hospital,hospital,hospital));
        request.setAttribute("doctor",doctor1);
        request.setRequestURI("/saveDoctor");
        request.setMethod("POST");
        model.setViewName("/index");
        Integer [] k={1,3};
        hospitals.add(hospital);
        hospitals.add(hospital);
        hospitals.add(hospital);
        model.addObject("hospitals",hospitals);
        Assert.assertEquals(doctorController.addDoctor( doctor1, model,request), model);
        Assert.assertEquals(200, response.getStatus());

    }
    @Test
    public void addDoctorTestElse(){
        List<Hospital> hospitals=new ArrayList<Hospital>();
        Hospital hospital=new Hospital();
        hospital.setId(10);
        hospital.setName("CARE");
        hospital.setCityName("Hyderabad");
        Doctor doctor1=new Doctor();
        doctor1.setFirstName("Krishna");
        doctor1.setLastName("Krishna");
        doctor1.setType("ENT");
        doctor1.setHospital(hospital);
        Doctor doctor=new Doctor();
        doctor.setId(12);
        doctor.setHospital(hospital);
        hospital.setDoctors(Arrays.asList(doctor1,doctor));
        when(hospitalService.getHospital(doctor1.getHospital().getId())).thenReturn(doctor1.getHospital());
        when(hospitalService.getAllHospitals()).thenReturn(Arrays.asList(hospital,hospital,hospital));
        request.setAttribute("doctor",doctor1);
        request.setRequestURI("/saveDoctor");
        request.setMethod("POST");
        model.setViewName("/index");
        Integer [] k={1,3};
        hospitals.add(hospital);
        hospitals.add(hospital);
        hospitals.add(hospital);
        model.addObject("hospitals",hospitals);
        Assert.assertEquals(doctorController.addDoctor( doctor1, model,request), model);
        Assert.assertEquals(200, response.getStatus());

    }
    @Test
    public void viewAllDoctorsTest(){
        Doctor doctor=new Doctor();
        Doctor doctor1=new Doctor();
        Hospital hospital=new Hospital();
        hospital.setId(1);
        when(doctorService.getUnAssignedDoctors()).thenReturn(Arrays.asList(doctor,doctor1));
        when(hospitalService.getHospital(1)).thenReturn(hospital);
        request.setParameter("hospitalId","1");
        request.setMethod("GET");
        model.setViewName("viewAllDoctors");
        Assert.assertEquals(doctorController.viewAllDoctors(model,request), model);
    }
    @Test
    public void viewAllDoctorsTestElse(){
        Doctor doctor=new Doctor();
        Doctor doctor1=new Doctor();
        Hospital hospital=new Hospital();
        hospital.setId(1);
        when(doctorService.getUnAssignedDoctors()).thenReturn(Arrays.asList());
        when(hospitalService.getHospital(1)).thenReturn(hospital);
        request.setParameter("hospitalId","1");
        request.setMethod("GET");
        model.setViewName("viewAllDoctors");
        Assert.assertEquals(doctorController.viewAllDoctors(model,request), model);
    }
    @Test
    public void viewDoctorsTest(){
        Hospital hospital=new Hospital();
        Doctor doctor=new Doctor();
        Doctor doctor1=new Doctor();
        hospital.setId(1);
        hospital.setName("CARE");
        hospital.setCityName("Hyderabad");

        doctor.setId(1);
        doctor1.setId(2);

        doctor.setHospital(hospital);
        doctor1.setHospital(hospital);
        List<Doctor> doctors=new ArrayList<Doctor>();
        doctors.add(doctor);
        doctors.add(doctor1);
        hospital.setDoctors(doctors);
        System.out.println(hospital.getDoctors());
        when(hospitalService.getHospital(hospital.getId())).thenReturn(hospital);
        request.setParameter("id","1");
        request.setMethod("GET");
        model.setViewName("viewDoctors");
        Assert.assertEquals(doctorController.viewDoctors(model,request), model);
    }
    @Test
    public void editDoctorTest(){
        Doctor doctor=new Doctor();
        doctor.setId(1);
        doctor.setFirstName("Krishna");
        doctor.setLastName("Teja");
        doctor.setType("ENT");
        when(doctorService.getDoctor(1)).thenReturn(doctor);
        request.setParameter("id","1");
        request.setMethod("GET");
        model.setViewName("addDoctor");
        Assert.assertEquals(doctorController.editDoctor(model,request), model);
    }
    @Test
    public void searchDoctorTest(){
        Hospital hospital=new Hospital();
        hospital.setId(1);
        Doctor doctor=new Doctor();
        doctor.setId(1);
        doctor.setFirstName("Krishna");
        doctor.setLastName("Teja");
        doctor.setType("SKIN");
        doctor.setHospital(hospital);
        Doctor doctor1=new Doctor();
        doctor1.setId(2);
        doctor1.setFirstName("Kiran");
        doctor1.setLastName("Kumar");
        doctor1.setType("ENT");
        when(doctorService.searchDoctor("K",hospital)).thenReturn(Arrays.asList(doctor,doctor1));
        request.setParameter("hospitalId","1");
        request.setMethod("GET");
        model.setViewName("viewDoctors");
        Assert.assertEquals(doctorController.searchDoctor(model,request), model);
    }
    @Test
    public void deleteDoctorTest(){
        Hospital hospital=new Hospital();
        Hospital hospital1=new Hospital();
        Doctor doctor=new Doctor();
        doctor.setId(1);
        PatientAppointment appointment=new PatientAppointment();
        appointment.setId(1);
        appointment.setTime("16:20");
        appointment.setDate(new Date(20180424).toLocalDate());
        Patient patient=new Patient();
        patient.setId(1);
        patient.setAppointment(appointment);
        appointment.setPatient(patient);
        doctor.setAppointments(Arrays.asList(appointment,appointment,appointment));
        when(doctorService.getDoctor(1)).thenReturn(doctor);
        when(patientService.getPatient(1)).thenReturn(patient);
        when(hospitalService.getAllHospitals()).thenReturn(Arrays.asList(hospital,hospital,hospital1));
        request.setParameter("id","1");
        request.setMethod("GET");
        model.setViewName("/index");
        model.addObject("hospitals",Arrays.asList(hospital,hospital,hospital1));
        Assert.assertEquals(doctorController.deleteDoctor(model,request), model);
    }
}
