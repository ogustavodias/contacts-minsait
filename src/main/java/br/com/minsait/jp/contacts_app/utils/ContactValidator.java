package br.com.minsait.jp.contacts_app.utils;

import br.com.minsait.jp.contacts_app.enums.ContactType;
import br.com.minsait.jp.contacts_app.models.Contact;

public class ContactValidator {
  private ContactValidator() {
  }

  public static Contact isValidContact(Contact contact) throws IllegalArgumentException {
    ContactType contactType = contact.getContactType();
    String contactValue = contact.getContactValue();

    switch (contactType) {

      case EMAIL:
        if (!ContactType.EMAIL.matches(contactValue)) {
          throw new IllegalArgumentException("'EMAIL' inválido");
        }
        break;

      case PHONE:
        if (!ContactType.PHONE.matches(contactValue)) {
          throw new IllegalArgumentException("'PHONE' inválido");
        }
        break;

      default:
        throw new IllegalArgumentException("O campo 'contactType' deve ser 'EMAIL' ou 'PHONE'");
    }

    return contact;
  }
}
