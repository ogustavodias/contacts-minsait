package br.com.minsait.jp.contacts_app.dto;

import br.com.minsait.jp.contacts_app.interfaces.DTO;
import br.com.minsait.jp.contacts_app.models.Person;
import br.com.minsait.jp.contacts_app.validations.AtLeastOneField;
import br.com.minsait.jp.contacts_app.validations.NullButNotBlank;

/**
 * Classe DTO que permite update parcial e total da entidade no banco de dados.
 */
@AtLeastOneField(fields = { "name" })
public class PersonUpdateDTO implements DTO<Person> {

  @NullButNotBlank(fieldName = "name")
  private String name;

  @Override
  public Person toPersistEntity(Person person) {
    if (this.name != null)
      person.setName(this.name);

    return person;
  }

  public void setName(String name) {
    this.name = name;
  }

}
