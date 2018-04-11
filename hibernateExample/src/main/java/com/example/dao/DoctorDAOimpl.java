package com.example.dao;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorDAOimpl implements DoctorDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addDoctor(Doctor doctor) {
        sessionFactory.getCurrentSession().saveOrUpdate(doctor);
    }

    @Override
    public void deleteDoctor(Integer doctor_id) {
        System.out.println("Delete Doctor----->"+doctor_id);
        Doctor doctor = (Doctor) sessionFactory.getCurrentSession().load(Doctor.class, doctor_id);
        if(null != doctor){
            doctor.setId(doctor_id);
           List<PatientAppointment> appointments=doctor.getAppointments();
           /*for(int i=0;i<appointments.size();i++){
               System.out.println("Removed-->"+appointments.get(i).getPatient().getFirstName());
//               appointments.remove(appointments.get(i));
//               Patient patient=appointments.get(i).getPatient();
               this.sessionFactory.getCurrentSession().delete(appointments.get(i));
           }*/
            doctor.getAppointments().clear();
            this.sessionFactory.getCurrentSession().delete(doctor);
            System.out.println("Delete Doctor----->"+doctor_id);
        }
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        sessionFactory.getCurrentSession().update(doctor);
        return doctor;
    }

    @Override
    public Doctor getDoctor(Integer doctor_id) {
        return (Doctor) sessionFactory.getCurrentSession().get(
                Doctor.class, doctor_id);
    }

    @Override
    public List<Doctor> getAll() {
        Query query=sessionFactory.getCurrentSession().getNamedQuery("findAllDoctors");
        return query.list();
    }

    @Override
    public List<Doctor> getUnAssigned() {
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Doctor.class)
                .add(Restrictions.isNull("hospital"));
        return criteria.list();
    }

    @Override
    public List<Doctor> searchDoctor(String firstName,Hospital hospital) {
        System.out.println("DAOImpl--->"+hospital.getName());
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Doctor.class)
          .add(Restrictions.and((Restrictions.eq("hospital",hospital)),(Restrictions.like("firstName", "%"+firstName+"%").ignoreCase()))) ;
        return criteria.list();
    }

}
