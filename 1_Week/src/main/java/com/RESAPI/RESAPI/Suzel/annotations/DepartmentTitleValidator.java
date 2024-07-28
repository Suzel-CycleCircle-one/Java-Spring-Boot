package com.RESAPI.RESAPI.Suzel.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class DepartmentTitleValidator implements ConstraintValidator<DepatmentTitleValidation, String> {
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        List<String> roles = List.of("HR Department", "IT Department", "Mechanical Department", "Opration Department");
        return roles.contains(inputRole);
    }
}
