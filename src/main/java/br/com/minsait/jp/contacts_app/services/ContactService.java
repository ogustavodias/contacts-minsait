package br.com.minsait.jp.contacts_app.services;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.minsait.jp.contacts_app.dto.ContactInsertDTO;
import br.com.minsait.jp.contacts_app.dto.ContactUpdateDTO;
import br.com.minsait.jp.contacts_app.enums.ContactType;
import br.com.minsait.jp.contacts_app.models.Contact;
import br.com.minsait.jp.contacts_app.models.Person;
import br.com.minsait.jp.contacts_app.repositorys.ContactRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ContactService {

  private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

  @Autowired
  private ContactRepository repository;

  @Autowired
  private PersonService personService;

  public List<Contact> getAllContactsByPersonId(Long personId) {
    logger.info("Searching all contacts of person with id {} ...", personId);
    return repository.findAllByPersonId(personId);
  }

  public Contact getContactById(Long id) {
    logger.info("Searching contact with id {}", id);
    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Contato de id " + id + " não encontrado"));
  }

  public Contact insertContact(ContactInsertDTO contactInsertDTO) {
    Person person = personService.getPersonById(contactInsertDTO.personId());
    Contact contact = new Contact.Builder()
        .setContactType(contactInsertDTO.contactType())
        .setContactValue(contactInsertDTO.contactValue())
        .setPerson(person)
        .build();
    checkIfIsValidContact(contact);
    logger.info("Saving contact {}...", contact);
    return repository.save(contact);
  }

  public Contact updateContact(Long id, ContactUpdateDTO contact) {
    logger.info("Searching contact with id {} to update...", id);
    Contact contactToUpdate = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Contato de id " + id + " não encontrado"));

    return repository.save(contact.toPersistEntity(contactToUpdate));
  }

  public Contact deleteContact(Long id) {
    logger.info("Searching contact with id {} to delete...", id);
    Contact contactToDelete = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Contato não encontrado"));

    repository.delete(contactToDelete);
    return contactToDelete;
  }

  public Boolean checkIfIsValidContact(Contact contact) throws IllegalArgumentException {
    Pattern EMAIL_PATTERN = Pattern
        .compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    Pattern PHONE_PATTERN = Pattern
        .compile("^\\+?\\d{0,2}\\s?\\(?\\d{2,3}\\)?\\s?\\d{4,5}-?\\d{4}$");

    ContactType contactType = contact.getContactType();
    String contactValue = contact.getContactValue();

    switch (contactType) {

      case EMAIL:
        if (!EMAIL_PATTERN.matcher(contactValue).matches()) {
          throw new IllegalArgumentException("'EMAIL' inválido");
        }
        return true;

      case PHONE:
        if (!PHONE_PATTERN.matcher(contactValue).matches()) {
          throw new IllegalArgumentException("'PHONE' inválido");
        }
        return true;

      default:
        throw new IllegalArgumentException("O campo 'contactType' deve ser preenchido com 'EMAIL' ou 'PHONE'");

    }

  }

}
