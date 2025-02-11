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

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ContactType contactType;

  @Column(nullable = false, unique = true)
  private String contactValue;

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

  private Contact(Builder builder) {
    this.id = builder.id;
    this.contactType = builder.contactType;
    this.contactValue = builder.contactValue;
    this.person = builder.person;
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

  public void setContactType(ContactType contactType) {
    this.contactType = contactType;
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
    return String.format(
        "{ ID: %d | TYPE: %s | VALUE: %s | PERSON: %s }",
        this.id, this.contactType, this.contactValue, this.person);
  }

  // Builder
  public static class Builder {
    private Long id;
    private ContactType contactType;
    private String contactValue;
    private Person person;

    public Builder setId(Long id) {
      this.id = id;
      return this;
    }

    public Builder setContactType(ContactType contactType) {
      this.contactType = contactType;
      return this;
    }

    public Builder setContactValue(String contactValue) {
      this.contactValue = contactValue;
      return this;
    }

    public Builder setPerson(Person person) {
      this.person = person;
      return this;
    }

    public Contact build() {
      return new Contact(this);
    }

  }

}
