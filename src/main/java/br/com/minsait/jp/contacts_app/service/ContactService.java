package br.com.minsait.jp.contacts_app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.minsait.jp.contacts_app.enums.ContactType;
import br.com.minsait.jp.contacts_app.models.Contact;
import br.com.minsait.jp.contacts_app.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ContactService {

  private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

  @Autowired
  private ContactRepository repository;

  public List<Contact> getAllContacts() {
    logger.info("Searching all contacts...");
    return repository.findAll();
  }

  public Contact insertContact(Contact contact) {
    logger.info("Entering contact {}...", contact);
    return repository.save(contact);
  }

  public Contact updateContact(Long id, Contact contact) {
    logger.info("Searching contact with id {} to update...", id);
    Contact contactToUpdate = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Contato de id " + id + " não encontrado"));

    if (contact.getContactValue() != null) {
      if (contact.getContactValue().trim().isEmpty())
        throw new IllegalArgumentException("'contactValue' não pode ser em branco");

      contactToUpdate.setContactValue(contact.getContactValue().trim());
    }

    if (contact.getType() != null) {
      if (contact.getType() != ContactType.EMAIL && contact.getType() != ContactType.PHONE)
        throw new IllegalArgumentException("'type' deve ser EMAIL ou PHONE");
    }

    if (contact.getUser() != null)
      contactToUpdate.setUser(contact.getUser());

    return repository.save(contactToUpdate);
  }

  public Contact deleteContact(Long id) {
    logger.info("Searching contact with id {} to delete...", id);
    Contact contactToDelete = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Contato não encontrado"));

    repository.delete(contactToDelete);
    return contactToDelete;
  }
}
