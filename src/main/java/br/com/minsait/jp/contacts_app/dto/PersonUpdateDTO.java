package br.com.minsait.jp.contacts_app.dto;

import br.com.minsait.jp.contacts_app.interfaces.DTO;
import br.com.minsait.jp.contacts_app.models.Person;
import br.com.minsait.jp.contacts_app.validations.AtLeastOneField;
import br.com.minsait.jp.contacts_app.validations.NullButNotBlank;

/**
 * Classe DTO que permite update parcial e total da entidade no banco de dados.
 */
@AtLeastOneField(fields = { "name", "nickname" })
public class PersonUpdateDTO implements DTO<Person> {

  @NullButNotBlank(fieldName = "name")
  private String name;

  @NullButNotBlank(fieldName = "nickname")
  private String nickname;

  @Override
  public Person toPersistEntity(Person person) {
    if (this.name != null)
      person.setName(this.name);
    if (this.nickname != null)
      person.setNickname(this.nickname);
    return person;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

}
