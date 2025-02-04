package br.com.minsait.jp.contacts_app.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.minsait.jp.contacts_app.common.ApiResponse;
import br.com.minsait.jp.contacts_app.enums.ResponseType;
import br.com.minsait.jp.contacts_app.models.Contact;
import br.com.minsait.jp.contacts_app.service.ContactService;
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
public class ContactController {

  private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

  @Autowired
  private ContactService service;

  @GetMapping
  public ResponseEntity<ApiResponse<List<Contact>>> getAllContacts() {
    ApiResponse<List<Contact>> response = new ApiResponse<>();

    List<Contact> contacts = service.getAllContacts();
    response.setType(ResponseType.SUCCESS);
    response.setBody(contacts);
    response.setMessage("Lista obtida com sucesso.");
    logger.info("List obtained successfully");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

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

  // PathMapping devido a possibilidade de atualização parcial da entidade
  @PatchMapping("{id}")
  public ResponseEntity<ApiResponse<Contact>> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
    ApiResponse<Contact> response = new ApiResponse<>();

    Contact updatedContact = service.updateContact(id, contact);
    response.setType(ResponseType.SUCCESS);
    response.setBody(updatedContact);
    response.setMessage("Contato atualizado com sucesso.");
    logger.info("Contact updated successfully");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

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
