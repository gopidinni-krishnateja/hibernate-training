package com.example.modal;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;

public class AppointmentTest {
    PatientAppointment appointment=new PatientAppointment();
    @Test
    public void AppointmentTest(){
        Doctor doctor=new Doctor();
        Patient patient =new Patient();
        appointment.setId(1);
        appointment.setDoctor(doctor);
        appointment.setTime("15:30");
        appointment.setDate(new Date(20180424).toLocalDate());
        appointment.setPatient(patient);
        Assert.assertEquals(appointment.getId(),1);
        Assert.assertEquals(appointment.getDoctor(),doctor);
        Assert.assertEquals(appointment.getTime(),"15:30");
        Assert.assertEquals(appointment.getDate(),new Date(20180424).toLocalDate());
        Assert.assertEquals(appointment.getPatient(),patient);
    }
}
