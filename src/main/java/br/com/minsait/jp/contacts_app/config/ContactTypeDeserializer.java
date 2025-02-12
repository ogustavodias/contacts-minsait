package br.com.minsait.jp.contacts_app.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.minsait.jp.contacts_app.enums.ContactType;

/**
 * Desserializador personalizado de ContactType para evitar que o Jackson lance
 * uma exceção e delegar essa responsabilidade às validações personalizadas.
 */
public class ContactTypeDeserializer extends JsonDeserializer<ContactType> {

  @Override
  public ContactType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
    String value = Optional.ofNullable(p.getText()).orElse("").trim();

    return Arrays.stream(ContactType.values())
        .filter(type -> type.name().equalsIgnoreCase(value))
        .findFirst()
        .orElseGet(() -> ContactType.UNKNOWN);
  }
}
