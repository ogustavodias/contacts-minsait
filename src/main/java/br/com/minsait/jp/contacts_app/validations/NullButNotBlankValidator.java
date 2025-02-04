package br.com.minsait.jp.contacts_app.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Valida um atributo que pode ser nulo mas não em branco, útil para serviços de
 * update parcial de entidades persistidas com DTO.
 */
public class NullButNotBlankValidator implements ConstraintValidator<NullButNotBlank, String> {

  private String fieldName;

  @Override
  public void initialize(NullButNotBlank constraintAnnotation) {
    this.fieldName = constraintAnnotation.fieldName();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || !value.trim().isEmpty())
      return true;

    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(
        String.format("O campo '%s' não pode ser um texto em branco", fieldName))
        .addConstraintViolation();

    return false;
  }
}
