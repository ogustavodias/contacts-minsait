package br.com.minsait.jp.contacts_app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.minsait.jp.contacts_app.dto.ContactInsertDTO;
import br.com.minsait.jp.contacts_app.dto.ContactUpdateDTO;
import br.com.minsait.jp.contacts_app.dto.PersonInsertDTO;
import br.com.minsait.jp.contacts_app.dto.PersonUpdateDTO;
import br.com.minsait.jp.contacts_app.models.Contact;
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

    if (personInsertDTO.contacts() != null && !personInsertDTO.contacts().isEmpty()) {
      List<Contact> contacts = new ArrayList<>();

      for (ContactInsertDTO contactDto : personInsertDTO.contacts()) {
        Contact contact = new Contact.Builder()
            .setContactType(contactDto.contactType())
            .setContactValue(contactDto.contactValue())
            .setPerson(person)
            .build();
        contacts.add(contact);
      }

      person.setContacts(contacts);
    }

    return repository.save(person);
  }

  public Person getPersonById(Long id) {
    logger.info("Searching person with id {}", id);
    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Pessoa de id " + id + " não encontrada."));
  }

  public List<Person> getAllPersons() {
    logger.info("Searching all persons...");
    return repository.findAll();
  }

  public Person updatePersonById(Long id, PersonUpdateDTO personUpdateDTO) {
    Person person = this.getPersonById(id);

    if (!ObjectUtils.hasNonNullField(personUpdateDTO))
      throw new IllegalArgumentException("Nenhum campo enviado para update");

    if (personUpdateDTO.name() != null)
      person.setName(personUpdateDTO.name());
    if (personUpdateDTO.street() != null)
      person.setStreet(personUpdateDTO.street());
    if (personUpdateDTO.postalCode() != null)
      person.setPostalCode(personUpdateDTO.postalCode());
    if (personUpdateDTO.city() != null)
      person.setCity(personUpdateDTO.city());
    if (personUpdateDTO.state() != null)
      person.setState(personUpdateDTO.state());

    if (personUpdateDTO.contacts() != null) {
      List<Contact> updatedContacts = new ArrayList<>();

      for (ContactUpdateDTO contactDto : personUpdateDTO.contacts()) {
        Optional<Contact> existsContact = person.getContacts().stream()
            .filter(c -> c.getId().equals(contactDto.id()))
            .findFirst();

        if (existsContact.isPresent()) {
          existsContact.get().setContactValue(contactDto.contactValue());
          updatedContacts.add(existsContact.get());
        } else {
          Contact contact = new Contact.Builder()
              .setContactType(contactDto.contactType())
              .setContactValue(contactDto.contactValue())
              .setPerson(person)
              .build();
          updatedContacts.add(contact);
        }
      }

      // Atualiza os contatos mantendo a referência
      person.getContacts().retainAll(updatedContacts); // Remove apenas os contatos que não estão na nova lista
      updatedContacts.forEach(contact -> {
        if (!person.getContacts().contains(contact)) {
          person.getContacts().add(contact);
        }
      });
    }

    return repository.save(person);
  }

  public Person deletePersonById(Long id) {
    Person personToDelete = this.getPersonById(id);

    repository.delete(personToDelete);
    return personToDelete;
  }

}
