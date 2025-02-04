package br.com.minsait.jp.contacts_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.minsait.jp.contacts_app.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
