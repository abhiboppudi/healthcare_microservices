package com.pm.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AllowedValuesValidator implements ConstraintValidator<AllowedValues, Object> {

  private Set<String> allowedValues;

  @Override
  public void initialize(AllowedValues constraintAnnotation){
    allowedValues = new HashSet<>(Arrays.asList(constraintAnnotation.values()));
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
    if(value == null) {
      return false;
    }
    return allowedValues.contains(value.toString());
  }

}
