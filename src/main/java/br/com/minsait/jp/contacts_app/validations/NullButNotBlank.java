package br.com.minsait.jp.contacts_app.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Valida um atributo que pode ser nulo mas não em branco, útil para serviços de
 * update parcial de entidades persistidas com DTO.
 */
@Constraint(validatedBy = NullButNotBlankValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NullButNotBlank {
  String message() default "O campo não pode ser um texto em branco";
  String fieldName() default "";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
