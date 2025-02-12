package br.com.minsait.jp.contacts_app.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.minsait.jp.contacts_app.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

  List<Contact> findAllByPersonId(Long personId);

  Boolean existsByContactValue(String ContactValue);

}
