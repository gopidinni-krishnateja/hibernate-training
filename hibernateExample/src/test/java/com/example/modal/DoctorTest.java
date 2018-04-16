package com.example.modal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DoctorTest {

    Doctor doctor=new Doctor();
    @Spy
    List<PatientAppointment> appointments = new ArrayList<PatientAppointment>();
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

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
    public void testGetHospital(){
        Hospital hospital=new Hospital();
        hospital.setId(1);
        hospital.setName("CARE");
        hospital.setCityName("Hyderabad");
        doctor.setHospital(hospital);
       doctor.getHospital();
       Assert.assertEquals(doctor.getHospital(),hospital);
    }
    @Test
    public void testGetAppointments(){
        doctor.setAppointments(appointments);
        Assert.assertEquals(doctor.getAppointments(),appointments);
    }
    @Test
    public void testGetId(){
        int id=1;
        doctor.setId(id);
        Assert.assertEquals(doctor.getId(),id);
    }
    @Test
    public void testSetFirstName(){
        doctor.setFirstName("Krishna");
        Assert.assertEquals(doctor.getFirstName(),"Krishna");
    }
    @Test
    public void testSetLastName(){
        doctor.setLastName("Teja");
        Assert.assertEquals(doctor.getLastName(),"Teja");
    }
    @Test
    public void testType(){
        doctor.setType("ENT");
        Assert.assertEquals(doctor.getType(),"ENT");
    }
}
