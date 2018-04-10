package com.example.controller;

import com.example.modal.Patient;
import com.example.modal.PatientAppointment;
import com.example.service.AppointmentService;
import com.example.service.DoctorService;
import com.example.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AppointmentController {
    int doctorId;
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;
    @RequestMapping(value = "/viewMyPatients",method = RequestMethod.GET)
    public ModelAndView saveDoctorAppointment(HttpServletRequest request,ModelAndView modal){
        List<Patient> patients = patientService.getAllPatients();
        int doctorId=Integer.parseInt(request.getParameter("id"));
        List<PatientAppointment> appointments=doctorService.getDoctor(doctorId).getAppointments();
        if(appointments.size()==0){
            modal.setViewName("noAppointmentsFound");

        } else {
            modal.addObject("appointments",appointments);
            modal.addObject("doctorId",doctorId);
            modal.setViewName("viewDoctorAppointments");
        }

        return  modal;
    }
   /* @RequestMapping(value = "/deleteAppointment",method = RequestMethod.GET)
    public ModelAndView deleteDoctorAppointment(HttpServletRequest request, ModelAndView model){

    }*/

}
