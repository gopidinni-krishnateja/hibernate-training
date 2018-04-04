package com.example.controller;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
import com.example.modal.PatientAppointment;
import com.example.service.AppointmentService;
import com.example.service.DoctorService;
import com.example.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DoctorController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DoctorService doctorService;
    int hospitalId;

    @Autowired
    private AppointmentService appointmentService;
    @RequestMapping(value = "/newDoctor", method = RequestMethod.GET)
    public ModelAndView newDoctor(ModelAndView model,HttpServletRequest request) {
        hospitalId = Integer.parseInt(request.getParameter("id"));
        return new ModelAndView("addDoctor","hospital",hospitalId);
    }

    @RequestMapping(value ="/saveDoctor",method = RequestMethod.POST)
    public  ModelAndView addDoctor(@ModelAttribute Doctor doctor,ModelAndView model){
        Hospital hospital = hospitalService.getHospital(hospitalId);
        doctor.setHospital(hospital);
          if (doctor.getId() == 0) {
            doctorService.addDoctor(doctor);
        } else {
              doctorService.updateDoctor(doctor);
        }
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<PatientAppointment> appointments= appointmentService.getAllPatientAppointments();
        model.addObject("doctors",doctors);

        model.addObject("appointments",appointments);
        model.setViewName("viewDoctors");
        return model;
    }
    @RequestMapping(value ="/viewDoctors",method = RequestMethod.GET)
    public  ModelAndView viewDoctors(ModelAndView model){
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<PatientAppointment> appointments= appointmentService.getAllPatientAppointments();
        model.addObject("doctors",doctors);
        model.addObject("appointments",appointments);
        model.setViewName("viewDoctors");
        return model;
    }

}
