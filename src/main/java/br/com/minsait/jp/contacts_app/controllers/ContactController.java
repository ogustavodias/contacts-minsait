package br.com.minsait.jp.contacts_app.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.minsait.jp.contacts_app.common.ApiResponse;
import br.com.minsait.jp.contacts_app.dto.ContactUpdateDTO;
import br.com.minsait.jp.contacts_app.enums.ResponseType;
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
@Tag(name = "Contatos", description = "API para gerenciar contatos")
public class ContactController {

  private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

  @Autowired
  private ContactService service;

  @Operation(summary = "Listar CONTATOS da pessoa", description = "Retorna uma lista com todos os CONTATOS cadastrados de uma determinada pessoa")
  @GetMapping("person/{personId}")
  public ResponseEntity<ApiResponse<List<Contact>>> getAllContactsByPersonId(@PathVariable Long personId) {
    ApiResponse<List<Contact>> response = new ApiResponse<>();

    List<Contact> contacts = service.getAllContactsByPersonId(personId);
    response.setType(ResponseType.SUCCESS);
    response.setBody(contacts);
    response.setMessage("Lista obtida com sucesso.");
    logger.info("List obtained successfully");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Operation(summary = "Buscar CONTATO", description = "Busca e retorna um determinado CONTATO cadastrado no banco de dados")
  @GetMapping("{id}")
  public ResponseEntity<ApiResponse<Contact>> getContactById(@PathVariable Long id) {
    ApiResponse<Contact> response = new ApiResponse<>();

    Contact contact = service.getContactById(id);
    response.setType(ResponseType.SUCCESS);
    response.setBody(contact);
    response.setMessage("Contato obtido com sucesso.");
    logger.info("Contact obtained successfully");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Operation(summary = "Inserir CONTATO", description = "Insere um novo CONTATO na base de dados")
  @PostMapping
  public ResponseEntity<ApiResponse<Contact>> insertContact(@RequestBody @Valid Contact contact) {
    ApiResponse<Contact> response = new ApiResponse<>();

    Contact insertedContact = service.insertContact(contact);
    response.setType(ResponseType.SUCCESS);
    response.setBody(insertedContact);
    response.setMessage("Contato inserido com sucesso.");
    logger.info("Contact entered successfully");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @Operation(summary = "Atualizar CONTATO", description = "Atualiza informações de um CONTATO na base de dados")
  @PatchMapping("{id}") // PathMapping devido a possibilidade de atualização parcial da entidade
  public ResponseEntity<ApiResponse<Contact>> updateContact(@PathVariable Long id,
      @RequestBody @Valid ContactUpdateDTO contact) {
    ApiResponse<Contact> response = new ApiResponse<>();

    Contact updatedContact = service.updateContact(id, contact);
    response.setType(ResponseType.SUCCESS);
    response.setBody(updatedContact);
    response.setMessage("Contato atualizado com sucesso.");
    logger.info("Contact updated successfully");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Operation(summary = "Deletar CONTATO", description = "Deleta um determinado CONTATO da base de dados")
  @DeleteMapping("{id}")
  public ResponseEntity<ApiResponse<Contact>> deleteContact(@PathVariable Long id) {
    ApiResponse<Contact> response = new ApiResponse<>();

    Contact deletedContact = service.deleteContact(id);
    response.setType(ResponseType.SUCCESS);
    response.setBody(deletedContact);
    response.setMessage("Contato deletado com sucesso.");
    logger.info("Contact deleted successfully");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
