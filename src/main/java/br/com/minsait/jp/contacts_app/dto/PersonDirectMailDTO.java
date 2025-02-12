package br.com.minsait.jp.contacts_app.dto;

import java.util.Optional;

import br.com.minsait.jp.contacts_app.models.Person;

public record PersonDirectMailDTO(Long id, String name, String directMail) {
  public PersonDirectMailDTO(Person person) {
    this(person.getId(), person.getName(), formatDirectMail(person));
  }

  private static String formatDirectMail(Person person) {
    String street = Optional.ofNullable(person.getStreet()).orElse("Endereço não informado");
    String postalCode = Optional.ofNullable(person.getPostalCode()).orElse("CEP não informado");
    String city = Optional.ofNullable(person.getCity()).orElse("Cidade não informada");
    String state = Optional.ofNullable(person.getState()).orElse("Estado não informado");

    return String.format(
        "%s - CEP: %s - %s/%s",
        street, postalCode, city, state);
  }
}
