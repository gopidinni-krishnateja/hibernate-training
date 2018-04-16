package com.example.modal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

public class HospitalTest {
    Hospital hospital=new Hospital();
    @Spy
    List<Doctor> doctors = new ArrayList<Doctor>();
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    public List<Doctor> getDoctorList(){
        Doctor doctor=new Doctor();
        hospital.setId(1);
        hospital.setName("CARE");
        hospital.setCityName("Hyderabad");
        doctor.setId(1);
        doctor.setFirstName("Krishna");
        doctor.setLastName("Teja");
        doctor.setType("ENT");
        doctor.setHospital(hospital);
        Doctor doctor1=new Doctor();
        doctor1.setId(2);
        doctor1.setFirstName("Rama");
        doctor1.setLastName("Krishna");
        doctor1.setType("SKIN");
        doctor1.setHospital(hospital);
        doctors.add(doctor);
        doctors.add(doctor1);
        return doctors;
    }
    @Test
    public void testGetId(){
        hospital.setId(1);
        Assert.assertEquals(hospital.getId(),1);
    }
    @Test
    public void testGetName(){
        hospital.setName("CARE");
        Assert.assertEquals(hospital.getName(),"CARE");
    }
    @Test
    public void testGetCityName(){
        hospital.setCityName("Hyderabad");
        Assert.assertEquals(hospital.getCityName(),"Hyderabad");
    }
    @Test
    public void testGetDoctor(){
        hospital.setDoctors(doctors);
        Assert.assertEquals(hospital.getDoctors(),doctors);
    }
}
