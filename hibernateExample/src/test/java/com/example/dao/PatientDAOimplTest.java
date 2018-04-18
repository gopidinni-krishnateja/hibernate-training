package com.example.dao;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class PatientDAOimplTest {
    Patient patient=new Patient();
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    ProjectionList projectionList;
    @Mock
    PatientDAO patientDAO;
    @InjectMocks
    PatientDAOimpl patientDAOimpl;
    @Mock
    Criteria criteria;
    @Spy
    List<Patient> patients = new ArrayList<Patient>();
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        patients = getPatients();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createCriteria(Patient.class, "pa")).thenReturn(criteria);
        when(session.createCriteria(Doctor.class, "doc")).thenReturn(criteria);
        when(session.createCriteria(Hospital.class, "hos")).thenReturn(criteria);

    }
    private List<Patient> getPatients() {
        Patient patient=new Patient();
        PatientAppointment appointment=new PatientAppointment();
        Patient patient1=new Patient();
        Doctor doctor=new Doctor();
        doctor.setId(1);
        doctor.setFirstName("Krishna");
        doctor.setLastName("teja");
        doctor.setType("ENT");
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
    public void testAddDoctor(){
        Hospital hospital=new Hospital();
        hospital.setId(1);
        hospital.setName("CARE");
        hospital.setCityName("Hyderabad");
        Patient patient=new Patient();
        PatientAppointment appointment=new PatientAppointment();
        Doctor doctor=new Doctor();
        doctor.setId(1);
        doctor.setFirstName("Krishna");
        doctor.setLastName("teja");
        doctor.setType("ENT");
        doctor.setHospital(hospital);
        appointment.setId(1);
        appointment.setTime("16:40");
        appointment.setDate(new Date(20180424).toLocalDate());
        appointment.setDoctor(doctor);
        doctor.setAppointments(Arrays.asList(appointment,appointment));
        patient.setId(1);
        patient.setFirstName("Ravi");
        patient.setLastname("Kumar");
        patient.setGender("MALE");
        patient.setAge(26);
        patient.setAppointment(appointment);
        Assert.assertNotNull(patientDAO);
        session.update(patient);
        patientDAOimpl.addPatient(patient);
        verify(session,atLeastOnce()).saveOrUpdate(patient);
    }
    @Test
    public void testDeletePatient(){
        Assert.assertNotNull(patientDAO);
        sessionFactory.getCurrentSession().delete(1);
        session.load(PatientAppointment.class,1);
        patientDAOimpl.deletePatient(1);
        verify(session,atLeastOnce()).load(Patient.class,1);
    }
    @Test
    public void testUpdatePatient(){
        Assert.assertNotNull(patientDAO);
        doThrow(RuntimeException.class).when(patientDAO).updatePatient(patient);
        patientDAOimpl.updatePatient(patient);
        verify(patientDAOimpl, atLeastOnce()).updatePatient(patient);
    }
    @Test
    public void testGetAllPatients(){
        Assert.assertNotNull(patientDAO);
        criteria=session.createCriteria(PatientAppointment.class);
        session.createCriteria(Doctor.class);
        session.createCriteria(Hospital.class);
        /*mock(ProjectionList.class).add(Projections.property("id"));
       projectionList.add(Projections.property("id"));*/
        when(patientDAOimpl.getAllPatients()).thenReturn(getPatients());
        doThrow(RuntimeException.class).when(patientDAOimpl).getAllPatients();
    }
    @Test(expected = RuntimeException.class)
    public void testGetPatient(){
        Assert.assertNotNull(patientDAO);
        when(patientDAO.getPatient(1)).thenReturn(patient);
        when(patientDAOimpl.getPatient(1)).thenReturn(patient);
        verify(patientDAOimpl, atLeastOnce()).getPatient(1);
    }
}
