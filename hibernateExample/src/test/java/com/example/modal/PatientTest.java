package com.example.modal;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;

public class PatientTest {
    Patient patient=new Patient();
    PatientAppointment appointment =new PatientAppointment();
    @Mock
    Doctor doctor;
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetAppointment(){
        appointment.setId(1);
        appointment.setDate(new Date(20180424).toLocalDate());
        appointment.setTime("16:45");
        appointment.setDoctor(doctor);
        patient.setAppointment(appointment);
        Assert.assertEquals(patient.getAppointment(),appointment);
    }
    @Test
    public void testSetId(){
        patient.setId(1);
        Assert.assertEquals(patient.getId(),1);
    }
    @Test
    public void setFirstName(){
        patient.setFirstName("Krishna");
        Assert.assertEquals(patient.getFirstName(),"Krishna");
    }
    @Test
    public void setLastName(){
        patient.setLastname("teja");
        Assert.assertEquals(patient.getLastname(),"teja");
    }
    @Test
    public void setGender(){
        patient.setGender("MALE");
        Assert.assertEquals(patient.getGender(),"MALE");
    }
    @Test
    public void setAge(){
        patient.setAge(12);
        Assert.assertEquals(patient.getAge(),12);
    }
}
