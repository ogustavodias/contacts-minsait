package br.com.minsait.jp.contacts_app.dto;

import br.com.minsait.jp.contacts_app.models.Person;

public record PersonDirectMailDTO(Long id, String name, String directMail) {
  public PersonDirectMailDTO(Person person) {
    this(person.getId(), person.getName(), formatDirectMail(person));
  }

  private static String formatDirectMail(Person person) {
    return String.format("%s - CEP: %s - %s/%s", person.getStreet(), person.getPostalCode(),
        person.getCity(), person.getState());
  }
}
