package com.ikram.pfe.validators;

import com.ikram.pfe.exceptions.ObjectValidationException;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

@Component
public class ClassValidator<T> {

  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();

  public void validate(T clazz) {
    Set<ConstraintViolation<T>> violations = validator.validate(clazz);
    if (violations.size() > 0) {
      Set<String> errors = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
      throw new ObjectValidationException(errors, clazz.getClass().getName());
    }
  }

}
