package br.com.minsait.jp.contacts_app.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.minsait.jp.contacts_app.config.ContactTypeDeserializer;

@JsonDeserialize(using = ContactTypeDeserializer.class)
public enum ContactType {
  /**
   * 
   * <p>
   * EMAIL - Valida endereços de e-mail no formato padrão.
   * </p>
   *
   * <p>
   * Exemplos válidos:
   * </p>
   * <ul>
   * <li>teste@email.com</li>
   * <li>user.name+123@gmail.com</li>
   * <li>john-doe@empresa.com</li>
   * </ul>
   * 
   * <p>
   * Exemplos inválidos:
   * </p>
   * <ul>
   * <li>@email.com</li>
   * <li>user@.com</li>
   * <li>email@site..com</li>
   * </ul>
   */
  EMAIL("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"),
  /**
   * <p>
   * PHONE - Valida números de telefone em diversos formatos.
   * </p>
   * 
   * <p>
   * Exemplos válidos:
   * </p>
   * <ul>
   * <li>+55 (11) 91234-5678</li>
   * <li>+1 123-456-7890</li>
   * <li>(11) 91234-5678</li>
   * <li>11912345678</li>
   * </ul>
   * 
   * <p>
   * Exemplos inválidos:</li>
   * </p>
   * <ul>
   * <li>+55 11 9123</li>
   * <li>123456789012</li>
   * <li>+55-11-91234-5678</li>
   * </ul>
   */
  PHONE("^\\+?\\d{0,2}\\s?\\(?\\d{2,3}\\)?\\s?\\d{4,5}-?\\d{4}$"),
  /**
   * <p>
   * UNKNOW - Indica tipo inválido de ContactType.
   * </p>
   */
  UNKNOW("");

  private final String regex;

  ContactType(String regex) {
    this.regex = regex;
  }

  public String getRegex() {
    return regex;
  }

  public boolean matches(String value) {
    return value != null && value.matches(this.regex);
  }
}
