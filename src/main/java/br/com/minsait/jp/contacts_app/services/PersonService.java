package br.com.minsait.jp.contacts_app.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.minsait.jp.contacts_app.dto.PersonDirectMailDTO;
import br.com.minsait.jp.contacts_app.dto.PersonInsertDTO;
import br.com.minsait.jp.contacts_app.dto.PersonUpdateDTO;
import br.com.minsait.jp.contacts_app.models.Person;
import br.com.minsait.jp.contacts_app.repositorys.PersonRepository;
import br.com.minsait.jp.contacts_app.utils.ObjectUtils;
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

  public Person updatePersonById(Long id, PersonUpdateDTO personUpdateDTO) throws BadRequestException {
    logger.info("Searching person with id {} to update...", id);
    Person personToUpdate = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Pessoa de id " + id + " não encontrada."));

    if (!ObjectUtils.hasNonNullField(personUpdateDTO))
      throw new BadRequestException("Nenhum campo enviado para update");
    else {
      if (personUpdateDTO.name() != null)
        personToUpdate.setName(personUpdateDTO.name());
      if (personUpdateDTO.street() != null)
        personToUpdate.setStreet(personUpdateDTO.street());
      if (personUpdateDTO.postalCode() != null)
        personToUpdate.setPostalCode(personUpdateDTO.postalCode());
      if (personUpdateDTO.city() != null)
        personToUpdate.setCity(personUpdateDTO.city());
      if (personUpdateDTO.state() != null)
        personToUpdate.setState(personUpdateDTO.state());
    }

    return repository.save(personToUpdate);
  }

  public Person deletePersonById(Long id) {
    logger.info("Searching person with id {} to delete...", id);
    Person personToDelete = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

    repository.delete(personToDelete);
    return personToDelete;
  }

}
