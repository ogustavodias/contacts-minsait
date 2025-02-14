package br.com.minsait.jp.contacts_app;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.minsait.jp.contacts_app.dto.ContactInsertDTO;
import br.com.minsait.jp.contacts_app.enums.ContactType;
import br.com.minsait.jp.contacts_app.models.Contact;
import br.com.minsait.jp.contacts_app.models.Person;
import br.com.minsait.jp.contacts_app.services.ContactService;

@AutoConfigureMockMvc
@SpringBootTest
public class ContactControllerTest {

  @MockitoBean
  ContactService cService;

  @Autowired
  MockMvc mockMvc;

  @Test
  void insertContactSuccess() throws Exception {
    ContactInsertDTO contactRequest = new ContactInsertDTO(ContactType.EMAIL, "augusto@hotmail.com", 1l);
    Person person = new Person.Builder().setId(1l).setName("Augusto Oliveira").build();

    when(cService.insertContact(any(ContactInsertDTO.class)))
        .thenReturn(new Contact.Builder()
            .setContactType(contactRequest.contactType())
            .setContactValue(contactRequest.contactValue())
            .setPerson(person)
            .build());

    mockMvc.perform(post("/api/contacts")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(contactRequest)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.body.contactValue").value("augusto@hotmail.com"));
  }

  @Test
  void insertContactError() throws Exception {
    ContactInsertDTO contactRequestWithoutType = new ContactInsertDTO(null, "augusto@hotmail.com", 1l);

    mockMvc.perform(post("/api/contacts")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(contactRequestWithoutType)))
        .andExpect(status().isBadRequest());
  }

}
