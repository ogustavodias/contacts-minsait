package br.com.minsait.jp.contacts_app.validations;

import java.lang.reflect.Field;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator de AtLeastOneField.
 */
public class AtLeastOneFieldValidator implements ConstraintValidator<AtLeastOneField, Object> {

  private String[] fields;

  @Override
  public void initialize(AtLeastOneField constraintAnnotation) {
    this.fields = constraintAnnotation.fields();
  }

  @Override
  public boolean isValid(Object object, ConstraintValidatorContext context) {

    if (object == null)
      return false;

    try {

      for (String fieldName : this.fields) {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        Object value = field.get(object);
        if (value != null && !value.toString().isBlank())
          return true;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
        .addPropertyNode(fields[0])
        .addConstraintViolation();

    return false;
  }

}
