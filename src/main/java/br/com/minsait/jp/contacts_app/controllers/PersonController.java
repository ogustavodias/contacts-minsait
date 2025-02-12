package br.com.minsait.jp.contacts_app.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.minsait.jp.contacts_app.dto.ApiResponseDTO;
import br.com.minsait.jp.contacts_app.dto.PersonDirectMailDTO;
import br.com.minsait.jp.contacts_app.dto.PersonInsertDTO;
import br.com.minsait.jp.contacts_app.dto.PersonUpdateDTO;
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
@Tag(name = "Pessoas", description = "API para gerenciar Pessoas")
public class PersonController {

  private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

  @Autowired
  private PersonService service;

  @Operation(summary = "Criar Pessoa", description = "Cria e insere uma nova Pessoa no banco de dados")
  @PostMapping
  public ResponseEntity<ApiResponseDTO<Person>> insertPerson(@RequestBody @Valid PersonInsertDTO person) {
    Person insertedPerson = service.insertPerson(person);
    ApiResponseDTO<Person> response = ApiResponseDTO.success("Pessoa inserida com sucesso.", insertedPerson);

    logger.info("Person entered successfully: {}", insertedPerson);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @Operation(summary = "Obter Pessoa por ID", description = "Busca pelo ID da Pessoa no banco de dados e retorna a Pessoa")
  @GetMapping("{id}")
  public ResponseEntity<ApiResponseDTO<Person>> getPersonById(@PathVariable Long id) {
    Person person = service.getPersonById(id);
    ApiResponseDTO<Person> response = ApiResponseDTO.success("Pessoa obtida com sucesso.", person);

    logger.info("Person obtained successfully: {}", person);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Operation(summary = "Obter Pessoa por ID para mala direta", description = "Busca pelo ID da Pessoa no banco de dados e retorna uma Mala Direta com alguns dos dados da Pessoa")
  @GetMapping("directmail/{id}")
  public ResponseEntity<ApiResponseDTO<PersonDirectMailDTO>> getPersonDirectMailById(@PathVariable Long id) {
    PersonDirectMailDTO directMail = service.getPersonDirectMailById(id);
    ApiResponseDTO<PersonDirectMailDTO> response = ApiResponseDTO.success(
        "Mala direta obtida com sucesso.",
        directMail);

    logger.info("Direct Mail obtained successfully: {}", directMail);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Operation(summary = "Listar todas as Pessoas", description = "Retorna uma lista com todas as Pessoas cadastradas")
  @GetMapping
  public ResponseEntity<ApiResponseDTO<List<Person>>> getAllPersons() {
    List<Person> persons = service.getAllPersons();
    ApiResponseDTO<List<Person>> response = ApiResponseDTO.success("Lista obtida com sucesso.", persons);

    if (persons.isEmpty()) {
      logger.info("List obtained successfully, but is empty.");
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    logger.info("List obtained successfully. Total Persons: {}", persons.size());
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Operation(summary = "Atualizar Pessoa por ID", description = "Busca pelo ID da Pessoa no banco de dados e atualiza suas informações com os novos dados enviados")
  @PatchMapping("{id}") // PathMapping devido a possibilidade de atualização parcial da entidade
  public ResponseEntity<ApiResponseDTO<Person>> updatePersonById(@PathVariable Long id,
      @RequestBody @Valid PersonUpdateDTO person) {
    Person updatedPerson = service.updatePersonById(id, person);
    ApiResponseDTO<Person> response = ApiResponseDTO.success("Pessoa atualizada com sucesso.", updatedPerson);

    logger.info("Person updated successfully: {}", updatedPerson);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Operation(summary = "Deletar Pessoa por ID", description = "Busca pelo ID da Pessoa no banco de dados e deleta a Pessoa")
  @DeleteMapping("{id}")
  public ResponseEntity<ApiResponseDTO<Person>> deletePersonById(@PathVariable Long id) {
    Person deletedPerson = service.deletePersonById(id);
    ApiResponseDTO<Person> response = ApiResponseDTO.success("Pessoa deletada com sucesso.", deletedPerson);

    logger.info("Person deleted successfully: {}", deletedPerson);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
