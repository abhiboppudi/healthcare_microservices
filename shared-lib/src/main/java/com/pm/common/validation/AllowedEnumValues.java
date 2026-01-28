package com.pm.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowedEnumValuesValidator.class)
@Documented
public @interface AllowedEnumValues {
  Class<? extends Enum<?>> enumClass();
  String[] values();
  String message() default "Value is not allowed";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
