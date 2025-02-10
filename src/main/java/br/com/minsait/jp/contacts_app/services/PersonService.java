package br.com.minsait.jp.contacts_app.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.minsait.jp.contacts_app.dto.PersonDirectMailDTO;
import br.com.minsait.jp.contacts_app.dto.PersonInsertDTO;
import br.com.minsait.jp.contacts_app.dto.PersonUpdateDTO;
import br.com.minsait.jp.contacts_app.models.Person;
import br.com.minsait.jp.contacts_app.repositorys.PersonRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonService {

  private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

  @Autowired
  private PersonRepository repository;

  public Person insertPerson(PersonInsertDTO personInsertDTO) {
    logger.info("Entering person {}...", personInsertDTO);
    Person person = new Person.Builder()
        .setName(personInsertDTO.name())
        .setStreet(personInsertDTO.street())
        .setPostalCode(personInsertDTO.postalCode())
        .setCity(personInsertDTO.city())
        .setState(personInsertDTO.state())
        .build();
    return repository.save(person);
  }

  public Person getPersonById(Long id) {
    logger.info("Searching person with id {}", id);
    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Pessoa de id " + id + " não encontrada."));
  }

  public PersonDirectMailDTO getPersonDirectMailById(Long id) {
    Person person = getPersonById(id);
    logger.info("Getting direct mail for person with id {}", id);
    return new PersonDirectMailDTO(person);
  }

  public List<Person> getAllPersons() {
    logger.info("Searching all persons...");
    return repository.findAll();
  }

  public Person updatePersonById(Long id, PersonUpdateDTO person) {
    logger.info("Searching person with id {} to update...", id);
    Person personToUpdate = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Pessoa de id " + id + " não encontrada."));

    return repository.save(person.toPersistEntity(personToUpdate));
  }

  public Person deletePersonById(Long id) {
    logger.info("Searching person with id {} to delete...", id);
    Person personToDelete = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

    repository.delete(personToDelete);
    return personToDelete;
  }

}
