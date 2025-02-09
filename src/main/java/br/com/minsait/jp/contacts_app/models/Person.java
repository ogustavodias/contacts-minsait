package br.com.minsait.jp.contacts_app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_persons")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "'name' não pode ser nulo ou em branco")
  @Column(nullable = false)
  private String name;

  private String street;
  private String postalCode;
  private String city;
  private String state;

  @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE, orphanRemoval = true)
  @JsonManagedReference
  private List<Contact> contacts;

  public Person() {
  }

  public Person(Long id, String name, String street, String postalCode, String city, String state) {
    this.id = id;
    this.name = name;
    this.street = street;
    this.postalCode = postalCode;
    this.city = city;
    this.state = state;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  @Override
  public String toString() {
    return String.format(
        "{ ID: %d | NAME: %s | POSTAL CODE: %s | CITY: %s | STATE: %s }",
        this.id, this.name, this.postalCode, this.city, this.state);
  }

}
