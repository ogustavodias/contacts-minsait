package br.com.minsait.jp.contacts_app.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.minsait.jp.contacts_app.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
