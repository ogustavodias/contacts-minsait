package br.com.minsait.jp.contacts_app.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.minsait.jp.contacts_app.config.ContactTypeDeserializer;

@JsonDeserialize(using = ContactTypeDeserializer.class)
public enum ContactType {
  EMAIL, PHONE, UNKNOW
}
