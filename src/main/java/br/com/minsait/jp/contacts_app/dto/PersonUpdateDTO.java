package br.com.minsait.jp.contacts_app.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PersonUpdateDTO(
  @Size(min = 2, max = 100, message = "'name' deve ter entre 2 e 100 caracteres") 
  @Pattern(regexp = "^(?i)[a-záéíóúàèìòùç]+(?: [a-záéíóúàèìòùç]+)+$", message = "'name' deve ser um nome completo válido") 
  String name, 
  String street, 
  @Pattern(regexp = "^\\d{8}$", message = "'postalCode' deve ser um CEP válido, somente com números e 8 caracteres")
  String postalCode, 
  String city, 
  String state) {
}
