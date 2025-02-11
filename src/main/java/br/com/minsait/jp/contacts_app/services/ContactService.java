package br.com.minsait.jp.contacts_app.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
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
    Person person = personService.getPersonById(contactInsertDTO.personId());
    Contact contact = new Contact.Builder()
        .setContactType(contactInsertDTO.contactType())
        .setContactValue(contactInsertDTO.contactValue())
        .setPerson(person)
        .build();
    logger.info("Saving contact {}...", contact);
    return repository.save(isValidContact(contact));
  }

  public Contact getContactById(Long id) {
    logger.info("Searching contact with id {}", id);
    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Contato de id " + id + " não encontrado"));
  }

  public List<Contact> getAllContactsByPersonId(Long personId) {
    logger.info("Searching all contacts of person with id {} ...", personId);
    return repository.findAllByPersonId(personId);
  }

  public Contact updateContactById(Long id, ContactUpdateDTO contactUpdateDTO) throws BadRequestException {
    logger.info("Searching contact with id {} to update...", id);
    Contact contact = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Contato de id " + id + " não encontrado"));

    if (!ObjectUtils.hasNonNullField(contact))
      throw new BadRequestException("Nenhum campo enviado para update");
    else {
      if (contactUpdateDTO.contactType() != null)
        contact.setContactType(contactUpdateDTO.contactType());
      if (contactUpdateDTO.contactValue() != null)
        contact.setContactValue(contactUpdateDTO.contactValue());
      if (contactUpdateDTO.personId() != null) {
        Person person = personService.getPersonById(contactUpdateDTO.personId());
        contact.setPerson(person);
      }
    }

    return repository.save(isValidContact(contact));
  }

  public Contact deleteContactById(Long id) {
    logger.info("Searching contact with id {} to delete...", id);
    Contact contactToDelete = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Contato não encontrado"));

    repository.delete(contactToDelete);
    return contactToDelete;
  }

  public Contact isValidContact(Contact contact) throws IllegalArgumentException {

    ContactType contactType = contact.getContactType();
    String contactValue = contact.getContactValue();

    switch (contactType) {

      case EMAIL:
        if (!ContactType.EMAIL.matches(contactValue)) {
          throw new IllegalArgumentException("'EMAIL' inválido");
        }
        break;

      case PHONE:
        if (!ContactType.PHONE.matches(contactValue)) {
          throw new IllegalArgumentException("'PHONE' inválido");
        }
        break;

      default:
        throw new IllegalArgumentException("O campo 'contactType' deve ser preenchido com 'EMAIL' ou 'PHONE'");
    }

    return contact;
  }

}
