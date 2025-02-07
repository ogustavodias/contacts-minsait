package br.com.minsait.jp.contacts_app.config;

import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.minsait.jp.contacts_app.enums.ContactType;

/**
 * Desserializador personalizado de ContactType para evitar que o jackson lance
 * uma exceção e deixar essa responsabilidade com o IsContact.
 */
public class ContactTypeDeserializer extends JsonDeserializer<ContactType> {

  @Override
  public ContactType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
    String value = p.getText().trim();

    if (value == null)
      return null;

    if (ContactType.UNKNOW.name().equalsIgnoreCase(value))
      return ContactType.UNKNOW;

    return Arrays.stream(ContactType.values())
        .filter(type -> type.name().equalsIgnoreCase(value))
        .findFirst()
        .orElseGet(() -> ContactType.UNKNOW);
  }
}
