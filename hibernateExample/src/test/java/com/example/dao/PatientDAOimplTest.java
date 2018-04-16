package com.example.dao;

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
import static org.mockito.Mockito.when;

public class PatientDAOimplTest {
    Patient patient=new Patient();
    @Mock
    PatientDAO patientDAO;
    @InjectMocks
    PatientDAOimpl patientDAOimpl;
    @Spy
    List<Patient> patients = new ArrayList<Patient>();
    @Before
    public void setUp() {
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
    @Test(expected = RuntimeException.class)
    public void testAddDoctor(){
        Assert.assertNotNull(patientDAO);
        doThrow(RuntimeException.class).when(patientDAO).addPatient(patient);
        patientDAOimpl.addPatient(patient);
        verify(patientDAOimpl, atLeastOnce()).addPatient(patient);
    }
    @Test(expected = RuntimeException.class)
    public void testDeletePatient(){
        Assert.assertNotNull(patientDAO);
        doThrow(RuntimeException.class).when(patientDAO).deletePatient(1);
        patientDAOimpl.deletePatient(1);
        verify(patientDAOimpl, atLeastOnce()).deletePatient(1);
    }
    @Test(expected = RuntimeException.class)
    public void testUpdatePatient(){
        Assert.assertNotNull(patientDAO);
        doThrow(RuntimeException.class).when(patientDAO).updatePatient(patient);
        patientDAOimpl.updatePatient(patient);
        verify(patientDAOimpl, atLeastOnce()).updatePatient(patient);
    }
    @Test(expected = RuntimeException.class)
    public void testGetAllPatients(){
        Assert.assertNotNull(patientDAO);
        when(patientDAO.getAllPatients()).thenReturn(patients);
        when(patientDAOimpl.getAllPatients()).thenReturn(patients);
        verify(patientDAOimpl, atLeastOnce()).getAllPatients();
        verify(patientDAOimpl,atLeastOnce()).getAllPatients();
    }
    @Test(expected = RuntimeException.class)
    public void testGetPatient(){
        Assert.assertNotNull(patientDAO);
        when(patientDAO.getPatient(1)).thenReturn(patient);
        when(patientDAOimpl.getPatient(1)).thenReturn(patient);
        verify(patientDAOimpl, atLeastOnce()).getPatient(1);
    }
}
