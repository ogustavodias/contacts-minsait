package br.com.minsait.jp.contacts_app.dto;

import br.com.minsait.jp.contacts_app.enums.ContactType;
import br.com.minsait.jp.contacts_app.interfaces.DTO;
import br.com.minsait.jp.contacts_app.models.Contact;
import br.com.minsait.jp.contacts_app.validations.AtLeastOneField;
import br.com.minsait.jp.contacts_app.validations.NullButNotBlank;

/**
 * Classe DTO que permite update parcial e total da entidade no banco de dados.
 */
@AtLeastOneField(fields = { "contactValue", "contactType" })
public class ContactUpdateDTO implements DTO<Contact> {

  @NullButNotBlank(fieldName = "contactValue")
  private String contactValue;

  private ContactType contactType;

  @Override
  public Contact toPersistEntity(Contact contact) {
    if (this.contactValue != null)
      contact.setContactValue(this.contactValue);
    if (this.contactType != null)
      contact.setType(this.contactType);
    return contact;
  }

  public void setContactValue(String contactValue) {
    this.contactValue = contactValue;
  }

  public void setContactType(ContactType contactType) {
    this.contactType = contactType;
  }
}
