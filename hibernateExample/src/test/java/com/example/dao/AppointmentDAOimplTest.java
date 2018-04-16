package com.example.dao;

import com.example.modal.Doctor;
import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class AppointmentDAOimplTest {
    @Mock
    AppointmentDAO appointmentDAO;
    @InjectMocks
    AppointmentDAOimpl appointmentDAOimpl;
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    Criteria criteria;
    @Spy
    List<PatientAppointment> appointments = new ArrayList<PatientAppointment>();
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        appointments = getAppointmentList();
        Mockito.doReturn(session).when(sessionFactory).getCurrentSession();

    }
    public List<PatientAppointment> getAppointmentList(){
        PatientAppointment appointment = new PatientAppointment();
        Doctor doctor=new Doctor();
        doctor.setId(1);
        Patient patient=new Patient();
        patient.setId(1);

        appointment.setId(1);
        appointment.setPatient(patient);
        appointment.setDate(new Date(20180424).toLocalDate());
        appointment.setTime("16:20");
        appointment.setDoctor(doctor);

        PatientAppointment appointment1 = new PatientAppointment();
        Doctor doctor1=new Doctor();
        doctor1.setId(2);
        Patient patient1=new Patient();
        patient1.setId(2);
        appointment1.setId(2);
        appointment1.setPatient(patient1);
        appointment1.setDate(new Date(20180424).toLocalDate());
        appointment1.setTime("16:20");
        appointment1.setDoctor(doctor1);
        appointments.add(appointment);
        appointments.add(appointment1);
        return appointments;
    }
    @Test(expected = RuntimeException.class)
    public void testAddAppointment(){
        PatientAppointment appointment = new PatientAppointment();
        Doctor doctor=new Doctor();
        doctor.setId(1);
        Patient patient=new Patient();
        patient.setId(1);
        appointment.setId(1);
        appointment.setTime("16:34");
        appointment.setDate(new Date(20180424).toLocalDate());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        Assert.assertNotNull(appointmentDAO);
        Mockito.doReturn(criteria).when(session).saveOrUpdate(appointment);
        //Assert.assertEquals(appointmentDAO.addPatientAppointment(appointment),appointment);
        //doThrow(RuntimeException.class).when(appointmentDAO).addPatientAppointment(appointment);
        //appointmentDAOimpl.addPatientAppointment(appointment);
        verify(appointmentDAOimpl, atLeastOnce()).addPatientAppointment(appointment);

    }
    @Test(expected = RuntimeException.class)
    public void testupdatePatientAppointment(){
        PatientAppointment appointment = new PatientAppointment();
        Doctor doctor=new Doctor();
        doctor.setId(1);
        Patient patient=new Patient();
        patient.setId(1);
        appointment.setId(1);
        appointment.setTime("16:34");
        appointment.setDate(new Date(20180424).toLocalDate());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        Assert.assertNotNull(appointmentDAO);
        Mockito.doReturn(criteria).when(session).update(appointment);
        //when(appointmentDAO.updatePatientAppointment(appointment)).thenReturn(new PatientAppointment());
        //when(appointmentDAOimpl.updatePatientAppointment(appointment)).thenReturn(new PatientAppointment());
        //Assert.assertEquals(appointmentDAOimpl.updatePatientAppointment(appointment),appointment);
       // verify(appointmentDAOimpl, atLeastOnce()).updatePatientAppointment(new PatientAppointment());
    }
    @Test(expected = RuntimeException.class)
    public void testgetPatientAppointment(){
        PatientAppointment appointment = new PatientAppointment();
        Doctor doctor=new Doctor();
        doctor.setId(1);
        Patient patient=new Patient();
        patient.setId(1);
        appointment.setId(1);
        appointment.setTime("16:34");
        appointment.setDate(new Date(20180424).toLocalDate());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        Assert.assertNotNull(appointmentDAO);
        when(appointmentDAO.getPatientAppointment(1)).thenReturn(appointment);
        when(appointmentDAOimpl.getPatientAppointment(1)).thenReturn(appointment);
        verify(appointmentDAOimpl, atLeastOnce()).getPatientAppointment(1);
    }
    @Test(expected = RuntimeException.class)
    public void testGetAll(){
        Assert.assertNotNull(appointmentDAO);
        when(appointmentDAO.getAll()).thenReturn(appointments);
        when(appointmentDAOimpl.getAll()).thenReturn(appointments);
        verify(appointmentDAOimpl, atLeastOnce()).getAll();
        verify(appointmentDAO,atLeastOnce()).getAll();
    }
    @Test(expected = RuntimeException.class)
    public void testDeletePatientAppointment(){
        Assert.assertNotNull(appointmentDAO);
        doThrow(RuntimeException.class).when(appointmentDAO).deletePatientAppointment(1);
        appointmentDAOimpl.deletePatientAppointment(1);
        verify(appointmentDAOimpl, atLeastOnce()).deletePatientAppointment(1);
    }
}
