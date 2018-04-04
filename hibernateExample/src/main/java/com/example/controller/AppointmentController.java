package com.example.controller;

import com.example.modal.Doctor;
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
    @RequestMapping(value = "/createAppointment",method = RequestMethod.GET)
    public ModelAndView createAppointment(ModelAndView model,HttpServletRequest request){
        return new ModelAndView("index");
    }
    @RequestMapping(value = "/saveAppointment",method = RequestMethod.POST)
    public ModelAndView saveAppointment(@ModelAttribute PatientAppointment patientAppointment,HttpServletRequest request){
        doctorId=Integer.parseInt(request.getParameter("id"));
        patientAppointment.setDate(patientAppointment.getDate().toString());
        patientAppointment.setTime(patientAppointment.getTime().toString());
        patientAppointment.setDoctor(doctorService.getDoctor(doctorId));
        appointmentService.addPatientAppointment(patientAppointment);
        List<Patient> patients= patientService.getAllPatients();
        return new ModelAndView("viewDoctorAppointments","patients",patients);
    }
    @RequestMapping(value = "saveDoctor", method = RequestMethod.GET)
    public ModelAndView saveDoctor(@ModelAttribute PatientAppointment patientAppointment,HttpServletRequest request){
        doctorId=Integer.parseInt(request.getParameter("id"));
        patientAppointment.setDoctor(doctorService.getDoctor(doctorId));
        appointmentService.updatePatientAppointment(patientAppointment);
        List<Patient> patients= patientService.getAllPatients();

        return new ModelAndView("viewDoctorAppointments","patients",patients);
    }

}
