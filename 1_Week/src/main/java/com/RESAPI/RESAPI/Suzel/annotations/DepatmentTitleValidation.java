package com.RESAPI.RESAPI.Suzel.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DepartmentTitleValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface DepatmentTitleValidation {

    String message() default "Invalid department title";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
