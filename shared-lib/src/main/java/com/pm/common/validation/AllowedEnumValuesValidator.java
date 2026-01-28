package com.pm.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AllowedEnumValuesValidator implements ConstraintValidator<AllowedEnumValues, Enum<?>> {

  private Set<String> allowedValues;

  @Override
  public void initialize(AllowedEnumValues constraintAnnotation) {
    allowedValues = new HashSet<String>(Arrays.asList(constraintAnnotation.values()));
  }

  @Override
  public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
    return value == null || allowedValues.contains(value.name());
  }

}
