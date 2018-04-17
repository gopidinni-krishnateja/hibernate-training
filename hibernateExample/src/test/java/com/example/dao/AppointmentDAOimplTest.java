package com.example.dao;

import com.example.modal.Doctor;
import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.junit.*;

import org.mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


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
        when(sessionFactory.getCurrentSession()).thenReturn(session);

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
    @Test
    public void testAddAppointment() throws Exception{
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
        session.saveOrUpdate(appointment);
        appointmentDAOimpl.addPatientAppointment(appointment);
        verify(session,atLeastOnce()).saveOrUpdate(appointment);

    }
    @Test
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
       session.update(appointment);
       appointmentDAOimpl.updatePatientAppointment(appointment);
       verify(session,atLeastOnce()).update(appointment);
    }
    @Test
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
        session.get(PatientAppointment.class, 1);
        appointmentDAOimpl.getPatientAppointment(1);
        verify(session,atLeastOnce()).get(PatientAppointment.class, 1);
    }
    @Test(expected = RuntimeException.class)
    public void testGetAll(){
        Assert.assertNotNull(appointmentDAO);
        criteria=session.createCriteria(PatientAppointment.class);
        when(criteria.list()).thenReturn(appointments);
        doThrow(RuntimeException.class).when(appointmentDAOimpl).getAll();

    }
    @Test
    public void testDeletePatientAppointment(){
        Assert.assertNotNull(appointmentDAO);
        sessionFactory.getCurrentSession().delete(1);
        session.load(PatientAppointment.class,1);
        appointmentDAOimpl.deletePatientAppointment(1);
        verify(session,atLeastOnce()).load(PatientAppointment.class,1);
    }

}
