package com.example.service;

import com.example.dao.DoctorDAO;
import com.example.modal.Doctor;
import com.example.modal.Hospital;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class DoctorServiceTest {
    Doctor doctor=new Doctor();
    Hospital hospital=new Hospital();
    @Mock
    DoctorDAO doctorDAO;
    @InjectMocks
    DoctorServiceimpl doctorServiceimpl;
    @Spy
    List<Doctor> doctors= new ArrayList<Doctor>();
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        doctors = getDoctorList();
        hospital.setDoctors(doctors);
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
    public void addDoctorTest(){
        doNothing().when(doctorDAO).addDoctor(doctor);
        doctorServiceimpl.addDoctor(doctor);
        verify(doctorDAO,atLeastOnce()).addDoctor(doctor);
    }
    @Test
    public void deleteDoctorTest(){
        int id=1;
        doctor.setId(id);
        doNothing().when(doctorDAO).deleteDoctor(id);
        doctorServiceimpl.deleteDoctor(id);
        verify(doctorDAO,atLeastOnce()).deleteDoctor(id);
    }
    @Test
    public void updateDoctorTest(){
        when(doctorDAO.updateDoctor(doctor)).thenReturn(doctor);
        doctorServiceimpl.updateDoctor(doctor);
        verify(doctorDAO,atLeastOnce()).updateDoctor(doctor);
    }
    @Test
    public void getAllDoctors(){
        when(doctorDAO.getAll()).thenReturn(doctors);
        Assert.assertEquals(doctorServiceimpl.getAllDoctors(),doctors);
    }
    @Test
    public void getDoctorTest(){
        int id=1;
        doctor.setId(id);
        when(doctorDAO.getDoctor(id)).thenReturn(doctor);
        doctorServiceimpl.getDoctor(id);
        verify(doctorDAO,atLeastOnce()).getDoctor(id);
    }
    @Test
    public void getUnAssignedDoctorsTest(){
        when(doctorDAO.getUnAssigned()).thenReturn(doctors);
        Assert.assertEquals(doctorServiceimpl.getUnAssignedDoctors(),doctors);
    }
    @Test
    public void searchDoctorTest(){
        when(doctorDAO.searchDoctor("Krishna",hospital)).thenReturn(doctors);
        Assert.assertEquals(doctorServiceimpl.searchDoctor("Krishna",hospital),doctors);
    }
}
