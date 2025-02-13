package br.com.minsait.jp.contacts_app.dto;

import br.com.minsait.jp.contacts_app.enums.ContactType;

public record ContactUpdateDTO(ContactType contactType, String contactValue) {
}
