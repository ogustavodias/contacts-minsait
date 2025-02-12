package br.com.minsait.jp.contacts_app.enums;

import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.minsait.jp.contacts_app.config.ContactTypeDeserializer;

@JsonDeserialize(using = ContactTypeDeserializer.class)
public enum ContactType {
  /**
   * EMAIL - Ex: person@hotmail.com
   */
  EMAIL("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"),
  /**
   * PHONE - Ex: (11) 91234-5678
   */
  PHONE("^\\+?\\d{0,2}\\s?\\(?\\d{2,3}\\)?\\s?\\d{4,5}-?\\d{4}$"),
  /**
   * UNKNOWN - Indica tipo inválido de ContactType
   */
  UNKNOWN("");

  private final Pattern pattern;

  ContactType(String regex) {
    this.pattern = Pattern.compile(regex);
  }

  public boolean matches(String value) {
    return value != null && pattern.matcher(value).matches();
  }
}
