package br.com.minsait.jp.contacts_app.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.minsait.jp.contacts_app.common.ApiResponse;
import br.com.minsait.jp.contacts_app.dto.PersonUpdateDTO;
import br.com.minsait.jp.contacts_app.enums.ResponseType;
import br.com.minsait.jp.contacts_app.models.Person;
import br.com.minsait.jp.contacts_app.services.PersonService;
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
@RequestMapping("/api/persons")
@Tag(name = "Pessoas", description = "API para gerenciar pessoas")
public class PersonController {

  private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

  @Autowired
  private PersonService service;

  @GetMapping
  @Operation(summary = "Listar PESSOAS", description = "Retorna uma lista com todas as PESSOAS cadastradas")
  public ResponseEntity<ApiResponse<List<Person>>> getAllPersons() {
    ApiResponse<List<Person>> response = new ApiResponse<>();

    List<Person> persons = service.getAllPersons();
    response.setType(ResponseType.SUCCESS);
    response.setBody(persons);
    response.setMessage("Lista obtida com sucesso.");
    logger.info("List obtained successfully");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("{id}")
  @Operation(summary = "Buscar PESSOA", description = "Busca e retorna uma determinada PESSOA cadastrada no banco de dados")
  public ResponseEntity<ApiResponse<Person>> getPersonById(@PathVariable Long id) {
    ApiResponse<Person> response = new ApiResponse<>();

    Person person = service.getPersonById(id);
    response.setType(ResponseType.SUCCESS);
    response.setBody(person);
    response.setMessage("Pessoa obtida com sucesso.");
    logger.info("Person obtained successfully");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping
  @Operation(summary = "Inserir PESSOA", description = "Insere uma nova PESSOA na base de dados")
  public ResponseEntity<ApiResponse<Person>> insertPerson(@RequestBody @Valid Person person) {
    ApiResponse<Person> response = new ApiResponse<>();

    Person insertedPerson = service.insertPerson(person);
    response.setType(ResponseType.SUCCESS);
    response.setBody(insertedPerson);
    response.setMessage("PESSOA inserida com sucesso.");
    logger.info("Person entered successfully");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  // PathMapping devido a possibilidade de atualização parcial da entidade
  @PatchMapping("{id}")
  @Operation(summary = "Atualizar PESSOA", description = "Atualizar informações de uma PESSOA na base de dados")
  public ResponseEntity<ApiResponse<Person>> updatePerson(@PathVariable Long id,
      @RequestBody @Valid PersonUpdateDTO person) {
    ApiResponse<Person> response = new ApiResponse<>();

    Person updatedPerson = service.updatePerson(id, person);
    response.setType(ResponseType.SUCCESS);
    response.setBody(updatedPerson);
    response.setMessage("PESSOA atualizada com sucesso.");
    logger.info("Person updated successfully");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("{id}")
  @Operation(summary = "Deletar PESSOA", description = "Deleta uma determinada PESSOA da base de dados")
  public ResponseEntity<ApiResponse<Person>> deletePerson(@PathVariable Long id) {
    ApiResponse<Person> response = new ApiResponse<>();

    Person deletedPerson = service.deletePerson(id);
    response.setType(ResponseType.SUCCESS);
    response.setBody(deletedPerson);
    response.setMessage("PESSOA deletada com sucesso.");
    logger.info("Person deleted successfully");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
