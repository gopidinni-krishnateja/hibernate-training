package com.example.dao;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
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
import static org.mockito.Mockito.when;

public class HospitalDAOimplTest {
    Hospital hospital =new Hospital();
    @Mock
    HospitalDAO hospitalDAO;
    @InjectMocks
    HospitalDAOimpl hospitalDAOimpl;
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    Criteria criteria;
    @Spy
    List<Hospital> hospitals = new ArrayList<Hospital>();
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        hospitals = getHospitals();
        when(sessionFactory.getCurrentSession()).thenReturn(session);

    }
    public List<Hospital> getHospitals() {
        Hospital hospital=new Hospital();
        Doctor doctor=new Doctor();
        doctor.setId(1);
        Doctor doctor1=new Doctor();
        doctor.setId(2);
        List<Doctor> doctors=new ArrayList<Doctor>();
        doctors.add(doctor);
        doctors.add(doctor1);
        hospital.setId(1);
        hospital.setName("CARE");
        hospital.setCityName("Hyderabad");
        hospital.setDoctors(doctors);
        Hospital hospital1=new Hospital();
        hospital1.setName("Yashodha");
        hospital1.setCityName("Bangalore");
        hospital1.setId(2);
        hospital1.setDoctors(doctors);
        hospitals.add(hospital);
        hospitals.add(hospital1);
        return hospitals;
    }
    @Test
    public void testAddHospital(){
        Hospital hospital=new Hospital();
        Doctor doctor=new Doctor();
        doctor.setId(1);
        Doctor doctor1=new Doctor();
        doctor.setId(2);
        List<Doctor> doctors=new ArrayList<Doctor>();
        doctors.add(doctor);
        doctors.add(doctor1);
        hospital.setId(1);
        hospital.setName("CARE");
        hospital.setCityName("Hyderabad");
        hospital.setDoctors(doctors);
        Assert.assertNotNull(hospitalDAO);
        session.saveOrUpdate(hospital);
        hospitalDAOimpl.addHospital(hospital);
        verify(session, atLeastOnce()).saveOrUpdate(hospital);
    }
    @Test
    public void testDeleteHospital(){
        hospital.setId(1);
        Assert.assertNotNull(hospitalDAO);
        sessionFactory.getCurrentSession().delete(1);
        session.load(Hospital.class,1);
        hospitalDAOimpl.deleteHospital(1);
        verify(session,atLeastOnce()).load(Hospital.class,1);
    }
    @Test
    public void updateHospital(){
        Assert.assertNotNull(hospitalDAO);
        session.update(hospital);
        verify(session, atLeastOnce()).update(hospital);
        hospitalDAOimpl.updateHospital(hospital);
        verify(session,atLeastOnce()).update(hospital);

    }
    @Test
    public void testGetHospital(){
        Hospital hospital=new Hospital();
        hospital.setId(1);
        hospital.setName("CARE");
        hospital.setCityName("Hyderabad");
        Assert.assertNotNull(hospitalDAO);
        session.get(Hospital.class,1);
        hospitalDAOimpl.getHospital(1);
        verify(session,atLeastOnce()).get(Hospital.class,1);
    }
    @Test(expected = RuntimeException.class)
    public void getAllTest(){
        Assert.assertNotNull(hospitalDAO);
        criteria=session.createCriteria(Hospital.class);
        when(criteria.list()).thenReturn(hospitals);
        doThrow(RuntimeException.class).when(hospitalDAOimpl).getAllHospitals();

    }
    @Test(expected = RuntimeException.class)
    public void updateHospitalTest(){
        Assert.assertNotNull(hospitalDAO);
        doThrow(RuntimeException.class).when(hospitalDAO).updateHospital(hospital);
        hospitalDAOimpl.updateHospital(hospital);
        verify(hospitalDAOimpl, atLeastOnce()).updateHospital(hospital);
    }
    @Test(expected = RuntimeException.class)
    public void getHospitalTest(){
        Assert.assertNotNull(hospitalDAO);
        when(hospitalDAO.getHospital(1)).thenReturn(hospital);
        when(hospitalDAOimpl.getHospital(1)).thenReturn(hospital);
        verify(hospitalDAOimpl, atLeastOnce()).getHospital(1);
    }
    @Test(expected = RuntimeException.class)
    public void testGetAll(){
        Assert.assertNotNull(hospitalDAO);
        when(hospitalDAO.getAllHospitals()).thenReturn(hospitals);
        when(hospitalDAOimpl.getAllHospitals()).thenReturn(hospitals);
        verify(hospitalDAOimpl, atLeastOnce()).getAllHospitals();
        verify(hospitalDAOimpl,atLeastOnce()).getAllHospitals();
    }
}
