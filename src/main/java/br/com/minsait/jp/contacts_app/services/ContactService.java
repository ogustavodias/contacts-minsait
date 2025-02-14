package br.com.minsait.jp.contacts_app.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.minsait.jp.contacts_app.dto.ContactInsertDTO;
import br.com.minsait.jp.contacts_app.dto.ContactUpdateDTO;
import br.com.minsait.jp.contacts_app.models.Contact;
import br.com.minsait.jp.contacts_app.models.Person;
import br.com.minsait.jp.contacts_app.repositorys.ContactRepository;
import br.com.minsait.jp.contacts_app.utils.ContactValidator;
import br.com.minsait.jp.contacts_app.utils.ObjectUtils;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ContactService {

  private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

  @Autowired
  private ContactRepository repository;

  @Autowired
  private PersonService personService;

  public Contact insertContact(ContactInsertDTO contactInsertDTO) {
    if (repository.existsByContactValue(contactInsertDTO.contactValue()))
      throw new IllegalArgumentException("Contato já cadastrado.");

    Person person = personService.getPersonById(contactInsertDTO.personId());
    Contact contact = new Contact.Builder()
        .setContactType(contactInsertDTO.contactType())
        .setContactValue(contactInsertDTO.contactValue())
        .setPerson(person)
        .build();
    logger.info("Saving contact {}...", contact);
    return repository.save(ContactValidator.isValidContact(contact));
  }

  public Contact getContactById(Long id) {
    logger.info("Searching contact with id {}", id);
    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Contato de id " + id + " não encontrado"));
  }

  public List<Contact> getAllContactsByPersonId(Long personId) {
    personService.getPersonById(personId); // if not found, throws EntityNotFound
    logger.info("Searching all contacts of person with id {} ...", personId);
    return repository.findAllByPersonId(personId);
  }

  public Contact updateContactById(Long id, ContactUpdateDTO contactUpdateDTO) {
    if (repository.existsByContactValue(contactUpdateDTO.contactValue()))
      throw new IllegalArgumentException("Contato já cadastrado.");

    logger.info("Searching contact with id {} to update...", id);
    Contact contact = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Contato de id " + id + " não encontrado"));

    if (!ObjectUtils.hasNonNullField(contactUpdateDTO))
      throw new IllegalArgumentException("Nenhum campo enviado para update");
    else {
      if (contactUpdateDTO.contactType() != null)
        contact.setContactType(contactUpdateDTO.contactType());
      if (contactUpdateDTO.contactValue() != null)
        contact.setContactValue(contactUpdateDTO.contactValue());
    }

    return repository.save(ContactValidator.isValidContact(contact));
  }

  public Contact deleteContactById(Long id) {
    logger.info("Searching contact with id {} to delete...", id);
    Contact contactToDelete = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Contato de id " + id + " não encontrado"));

    repository.delete(contactToDelete);
    return contactToDelete;
  }
}
