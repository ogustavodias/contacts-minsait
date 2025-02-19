package br.com.minsait.jp.contacts_app.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.minsait.jp.contacts_app.dto.ApiResponseDTO;
import br.com.minsait.jp.contacts_app.dto.ContactInsertDTO;
import br.com.minsait.jp.contacts_app.dto.ContactUpdateDTO;
import br.com.minsait.jp.contacts_app.models.Contact;
import br.com.minsait.jp.contacts_app.services.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/contacts")
@Tag(name = "Contatos", description = "API para gerenciar Contatos")
public class ContactController {

  private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

  @Autowired
  private ContactService service;

  @Operation(summary = "Adicionar um novo Contato a uma Pessoa", description = "Cria e insere um Contato de uma Pessoa no banco de dados")
  @PostMapping
  public ResponseEntity<ApiResponseDTO<Contact>> insertContact(@RequestBody @Valid ContactInsertDTO contact) {
    Contact insertedContact = service.insertContact(contact);
    ApiResponseDTO<Contact> response = ApiResponseDTO.success("Contato inserido com sucesso.", insertedContact);

    logger.info("Contact entered successfully: {}", insertedContact);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @Operation(summary = "Obter Contato por ID", description = "Busca pelo ID do Contato no banco de dados e retorna o Contato")
  @GetMapping("{id}")
  public ResponseEntity<ApiResponseDTO<Contact>> getContactById(@PathVariable Long id) {
    Contact contact = service.getContactById(id);
    ApiResponseDTO<Contact> response = ApiResponseDTO.success("Contato obtido com sucesso.", contact);

    logger.info("Contact obtained successfully: {}", contact);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Operation(summary = "Listar todos os Contatos de uma Pessoa", description = "Busca pelo ID de uma Pessoa no banco de dados e retorna uma lista com todos os seus Contatos")
  @GetMapping("person/{personId}")
  public ResponseEntity<ApiResponseDTO<List<Contact>>> getAllContactsByPersonId(@PathVariable Long personId) {
    List<Contact> contacts = service.getAllContactsByPersonId(personId);
    ApiResponseDTO<List<Contact>> response = ApiResponseDTO.success("Lista obtida com sucesso.", contacts);

    if (contacts.isEmpty()) {
      logger.info("List obtained successfully, but is empty.");
      ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    logger.info("List obtained successfully. Total Contacts of Person with id {}: {}.", personId, contacts.size());
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Operation(summary = "Atualizar Contato por ID", description = "Busca pelo ID do Contato no banco dados e atualiza suas informações com os novos dados enviados")
  @PatchMapping("{id}") // PathMapping devido a possibilidade de atualização parcial da entidade
  public ResponseEntity<ApiResponseDTO<Contact>> updateContact(
      @PathVariable Long id,
      @RequestBody @Valid ContactUpdateDTO contact) {
    Contact updatedContact = service.updateContactById(id, contact);
    ApiResponseDTO<Contact> response = ApiResponseDTO.success("Contato atualizado com sucesso.", updatedContact);

    logger.info("Contact updated successfully: {}", updatedContact);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Operation(summary = "Deletar Contato por ID", description = "Busca pelo ID do Contato no banco de dados e deleta o Contato")
  @DeleteMapping("{id}")
  public ResponseEntity<ApiResponseDTO<Contact>> deleteContact(@PathVariable Long id) {
    Contact deletedContact = service.deleteContactById(id);
    ApiResponseDTO<Contact> response = ApiResponseDTO.success("Contato deletado com sucesso.", deletedContact);

    logger.info("Contact deleted successfully: {}", deletedContact);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
