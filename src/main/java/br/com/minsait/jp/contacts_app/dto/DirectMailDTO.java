package br.com.minsait.jp.contacts_app.dto;

import br.com.minsait.jp.contacts_app.models.Person;

public record DirectMailDTO(Long id, String name, String directMail) {
  public DirectMailDTO(Person person) {
    this(person.getId(), person.getName(), formatDirectMail(person));
  }

  private static String formatDirectMail(Person person) {
    return String.format("%s - CEP: %s - %s/%s", person.getStreet(), person.getPostalCode(),
        person.getCity(), person.getState());
  }
}
