package br.com.minsait.jp.contacts_app.models;

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

  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ContactType type;

  @NotNull
  @NotBlank
  @Column(unique = true)
  private String contactValue;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

}
