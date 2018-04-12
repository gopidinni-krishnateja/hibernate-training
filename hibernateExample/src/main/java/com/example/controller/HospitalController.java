package com.example.controller;

import com.example.modal.Doctor;
import com.example.modal.Hospital;
import com.example.service.DoctorService;
import com.example.service.HospitalService;
import com.example.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HospitalController {



    public HospitalController() {
        System.out.println("HospitalController()");
    }

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @RequestMapping(value = "/index")
    public ModelAndView homePage()
    {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
       // System.out.println("hospitals---> "+hospitals.get(0).getDoctors().get(0).getFirstName());
        return new ModelAndView("index","hospitals",hospitals);
    }
    @RequestMapping(value = "/newHospital", method = RequestMethod.GET)
    public ModelAndView newHospital(ModelAndView model) {
        List<Doctor> doctors=doctorService.getUnAssignedDoctors();
        if(doctors.size()==0){
          model.setViewName("noDoctorsFound");
        } else {
            // System.out.println("DoctorsList--->"+doctors.get(0).getFirstName());
            model.addObject("hospital",new Hospital());
            model.addObject("doctors",doctors);
            model.setViewName("hospital");

        }
        return model;
    }
    @RequestMapping(value = "/saveHospital", method = RequestMethod.POST)
    public ModelAndView saveHospital(HttpServletRequest request) {
        int hospitalId=0;
        Hospital hospital=new Hospital();
        String[] doctorIds= request.getParameterValues("doctors");
     /*   int id=0;
        if(request.getParameter("hospitalId")!=null){
            hospitalId=Integer.parseInt(request.getParameter("hospitalId"));
           hospital=hospitalService.getHospital(hospitalId);
            hospital.setId(hospitalId);
        }*/

        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        for(int i=0;i<doctorIds.length;i++){
           int id=Integer.parseInt(doctorIds[i]);
            Doctor doctor=doctorService.getDoctor(id);
            doctors.add(doctor);
            doctor.setHospital(hospital);
        }
        hospital.setName(request.getParameter("name"));
        hospital.setCityName(request.getParameter("cityName"));
        hospital.setDoctors(doctors);
        if (hospital.getId() == 0) {
            hospitalService.addHospital(hospital);
        } else {
            hospitalService.updateHospital(hospital);
        }
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        return new ModelAndView("viewhospitals","hospitals",hospitals);
    }
    @RequestMapping(value = "/deleteHospital",method = RequestMethod.GET)
    public ModelAndView deleteDoctor(ModelAndView model,HttpServletRequest request){
        int hospitalId=Integer.parseInt(request.getParameter("id"));
        System.out.println("HospitalId---->"+hospitalId);
        hospitalService.deleteHospital(hospitalId);
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        //System.out.println("hospitals---> "+hospitals.get(0).getDoctors().get(0).getFirstName());
        return new ModelAndView("/index","hospitals",hospitals);
    }
    @RequestMapping(value = "/saveHospitalDoctors", method = RequestMethod.POST)
    public ModelAndView saveHospitalDoctors(HttpServletRequest request) {
        int hospitalId=0;
        Hospital hospital=new Hospital();
        String[] doctorIds= request.getParameterValues("doctors");
        int id=0;
        if(request.getParameter("hospitalId")!=null){
            hospitalId=Integer.parseInt(request.getParameter("hospitalId"));
            hospital=hospitalService.getHospital(hospitalId);
        }

        System.out.println("Hospital--->"+hospital.getName());
        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        for(int i=0;i<doctorIds.length;i++){
            id=Integer.parseInt(doctorIds[i]);
            Doctor doctor=doctorService.getDoctor(id);
            doctors.add(doctor);
            doctor.setHospital(hospital);
        }
        hospital.setDoctors(doctors);
            hospitalService.updateHospital(hospital);
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        return new ModelAndView("viewhospitals","hospitals",hospitals);
    }
    @RequestMapping(value = "/viewhospitals",method = RequestMethod.GET)
    public ModelAndView viewHospital(ModelAndView model){
        List<Hospital> hospitals=hospitalService.getAllHospitals();
        return new ModelAndView("viewhospitals","hospitals",hospitals);

    }


}
