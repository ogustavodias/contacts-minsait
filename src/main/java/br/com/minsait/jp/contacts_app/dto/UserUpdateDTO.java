package br.com.minsait.jp.contacts_app.dto;

import br.com.minsait.jp.contacts_app.interfaces.DTO;
import br.com.minsait.jp.contacts_app.models.User;
import br.com.minsait.jp.contacts_app.validations.AtLeastOneField;
import br.com.minsait.jp.contacts_app.validations.NullButNotBlank;

/**
 * Classe DTO que permite update parcial e total da entidade no banco de dados.
 */
@AtLeastOneField(fields = {"name", "nickname"})
public class UserUpdateDTO implements DTO<User> {

  @NullButNotBlank(fieldName = "name")
  private String name;

  @NullButNotBlank(fieldName = "nickname")
  private String nickname;

  @Override
  public User toPersistEntity(User user) {
    if (this.name != null)
      user.setName(this.name);
    if (this.nickname != null)
      user.setNickname(this.nickname);
    return user;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

}
