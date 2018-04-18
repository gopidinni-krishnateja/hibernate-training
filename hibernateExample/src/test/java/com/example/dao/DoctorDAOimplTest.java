package com.example.dao;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

public class DoctorDAOimplTest {
    @Mock
    DoctorDAO doctorDAO;
    @InjectMocks
    DoctorDAOimpl doctorDAOimpl;
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    Criteria criteria;
    @Spy
    List<Doctor> doctors = new ArrayList<Doctor>();
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createCriteria(Doctor.class)).thenReturn(criteria);
        doctors = getDoctorList();
    }
    Hospital hospital=new Hospital();
    Doctor doctor=new Doctor();
    public List<Doctor> getDoctorList(){
        Hospital hospital=new Hospital();
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
    public void testAddDoctor(){
        Assert.assertNotNull(doctorDAO);
        session.saveOrUpdate(doctor);
        doctorDAOimpl.addDoctor(doctor);
        verify(session, atLeastOnce()).saveOrUpdate(doctor);
    }
    @Test
    public void testDeleteDoctor(){
        Assert.assertNotNull(doctorDAO);
        sessionFactory.getCurrentSession().delete(1);
        session.load(Doctor.class,1);
        doctorDAOimpl.deleteDoctor(1);
        verify(session,atLeastOnce()).load(Doctor.class,1);
    }
    @Test
    public void testUpdateDoctor(){
        Assert.assertNotNull(doctorDAO);
        session.update(doctor);
        doctorDAOimpl.updateDoctor(doctor);
        verify(session,atLeastOnce()).update(doctor);
    }
    @Test
    public void testGetDoctor(){
        Assert.assertNotNull(doctorDAO);
        session.get(Doctor.class,1);
        doctorDAOimpl.getDoctor(1);
        verify(session, atLeastOnce()).get(Doctor.class,1);
    }
    @Test(expected = RuntimeException.class)
    public void testGetAll(){
        Assert.assertNotNull(doctorDAO);
        when(doctorDAO.getAll()).thenReturn(doctors);
        when(doctorDAOimpl.getAll()).thenReturn(doctors);
        verify(doctorDAOimpl, atLeastOnce()).getAll();
        verify(doctorDAO,atLeastOnce()).getAll();
    }
    @Test(expected = RuntimeException.class)
    public void testUnAssignedDoctors(){
        Assert.assertNotNull(doctorDAO);
        when(doctorDAO.getUnAssigned()).thenReturn(doctors);
        when(doctorDAOimpl.getUnAssigned()).thenReturn(doctors);
        verify(doctorDAOimpl, atLeastOnce()).getUnAssigned();
        verify(doctorDAO,atLeastOnce()).getUnAssigned();
    }
    @Test(expected = RuntimeException.class)
    public void testSearchDoctor(){
        Assert.assertNotNull(doctorDAO);
        when(doctorDAO.searchDoctor("Krishna",hospital)).thenReturn(doctors);
        when(doctorDAOimpl.searchDoctor("Krishna",hospital)).thenReturn(doctors);
        verify(doctorDAOimpl, atLeastOnce()).searchDoctor("Krishna",hospital);
    }

}
