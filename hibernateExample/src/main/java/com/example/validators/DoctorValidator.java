package com.example.validators;

import com.example.modal.Doctor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.util.Set;

@Component
public class DoctorValidator implements Validator{

    public boolean supports(Class<?> clazz) {
        return Doctor.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        Doctor doctor = (Doctor) target;
        String firstName=doctor.getFirstName();
        String lastName=doctor.getLastName();
        String type=doctor.getType();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.doctor.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.doctor.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "NotEmpty.doctor.type");


    }
}
