package com.example.service;

import com.example.dao.HospitalDAO;
import com.example.dao.PatientDAO;
import com.example.modal.Doctor;
import com.example.modal.Hospital;
import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PatientServiceTest {
    Patient patient=new Patient();
    @Mock
    PatientDAO patientDAO;
    @InjectMocks
    PatientServiceimpl patientServiceimpl;
    @Spy
    List<Patient> patients = new ArrayList<Patient>();
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        patients = getPatients();

    }
    private List<Patient> getPatients() {
        Patient patient=new Patient();
        PatientAppointment appointment=new PatientAppointment();
        Patient patient1=new Patient();
        Doctor doctor=new Doctor();
        appointment.setId(1);
        appointment.setTime("16:40");
        appointment.setDate(new Date(20180424).toLocalDate());
        appointment.setDoctor(doctor);
        patient.setId(1);
        patient.setFirstName("Ravi");
        patient.setLastname("Kumar");
        patient.setGender("MALE");
        patient.setAge(26);
        patient.setAppointment(appointment);
        patient1.setId(2);
        patient1.setFirstName("Vijay");
        patient1.setLastname("Kumar");
        patient1.setAge(28);
        patient1.setGender("MALE");
        patient1.setAppointment(appointment);
        patients.add(patient);
        patients.add(patient);
        return patients;
    }
    @Test
    public void addPatientTest(){
        doNothing().when(patientDAO).addPatient(patient);
        patientServiceimpl.addPatient(patient);
        verify(patientDAO,atLeastOnce()).addPatient(patient);
    }
    @Test
    public void deletePatientTest(){
        int id=1;
        patient.setId(id);
        doNothing().when(patientDAO).deletePatient(id);
        patientServiceimpl.deletePatient(id);
        verify(patientDAO,atLeastOnce()).deletePatient(id);
    }
    @Test
    public void updatePatient(){
        when(patientDAO.updatePatient(patient)).thenReturn(patient);
        patientServiceimpl.updatePatient(patient);
        verify(patientDAO,atLeastOnce()).updatePatient(patient);
    }
    @Test
    public void getAllPatients(){
        when(patientDAO.getAllPatients()).thenReturn(patients);
        Assert.assertEquals(patientServiceimpl.getAllPatients(),patients);
    }
    @Test
    public void getPatientTest(){
        int id=1;
        patient.setId(id);
        when(patientDAO.getPatient(id)).thenReturn(patient);
        patientServiceimpl.getPatient(id);
        verify(patientDAO,atLeastOnce()).getPatient(id);
    }

}
