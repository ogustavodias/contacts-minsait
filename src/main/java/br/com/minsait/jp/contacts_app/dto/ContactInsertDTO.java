package br.com.minsait.jp.contacts_app.dto;

import br.com.minsait.jp.contacts_app.enums.ContactType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContactInsertDTO(
    @NotNull(message = "'contactType' precisa ser informado") ContactType contactType,
    @NotBlank(message = "'contactValue' precisa ser informado") String contactValue,
    @NotNull(message = "'personId' precisa ser informado") Long personId) {
}
