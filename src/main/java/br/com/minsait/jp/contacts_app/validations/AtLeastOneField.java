package br.com.minsait.jp.contacts_app.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Valida se foi informado pelo menos um campo válido para o Update.
 */
@Documented
@Target(ElementType.TYPE) // Anotação a nível de classe
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeastOneFieldValidator.class)
public @interface AtLeastOneField {
    String message() default "Pelo menos um campo deve ser preenchido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    // Lista de campos a serem validados
    String[] fields();
}
