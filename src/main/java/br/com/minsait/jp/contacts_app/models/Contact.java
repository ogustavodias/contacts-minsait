package br.com.minsait.jp.contacts_app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.minsait.jp.contacts_app.enums.ContactType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_contacts")
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "'contactType' não deve ser nulo")
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ContactType contactType;

  @NotBlank(message = "'contactValue' não deve ser nulo ou em branco")
  @Column(nullable = false, unique = true)
  private String contactValue;

  @NotNull(message = "'person' deve ser informado juntamente com seu 'id'")
  @ManyToOne
  @JoinColumn(name = "person_id")
  @JsonBackReference
  private Person person;

  public Contact() {
  }

  public Contact(Long id, @NotNull ContactType contactType, @NotBlank String contactValue, @NotNull Person person) {
    this.id = id;
    this.contactType = contactType;
    this.contactValue = contactValue;
    this.person = person;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ContactType getContactType() {
    return contactType;
  }

  public void setType(ContactType type) {
    this.contactType = type;
  }

  public String getContactValue() {
    return contactValue;
  }

  public void setContactValue(String contactValue) {
    this.contactValue = contactValue;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  @Override
  public String toString() {
    return "{" + "Type: " + getContactType() + "| Value: " + getContactValue() + "| Person: " + getPerson() + "}";
  }

}
