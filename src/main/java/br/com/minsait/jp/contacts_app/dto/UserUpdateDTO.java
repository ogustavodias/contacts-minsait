package br.com.minsait.jp.contacts_app.dto;

import br.com.minsait.jp.contacts_app.models.User;
import br.com.minsait.jp.contacts_app.validations.NullButNotBlank;

/**
 * Classe DTO que permite update parcial e total da entidade no banco de dados.
 */
public class UserUpdateDTO {

  @NullButNotBlank(fieldName = "name")
  private String name;

  @NullButNotBlank(fieldName = "nickname")
  private String nickname;

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
