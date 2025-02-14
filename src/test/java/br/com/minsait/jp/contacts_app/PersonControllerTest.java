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

import br.com.minsait.jp.contacts_app.dto.PersonInsertDTO;
import br.com.minsait.jp.contacts_app.models.Person;
import br.com.minsait.jp.contacts_app.services.PersonService;

@AutoConfigureMockMvc
@SpringBootTest
public class PersonControllerTest {

  @MockitoBean
  PersonService pService;

  @Autowired
  MockMvc mockMvc;

  @Test
  void insertPersonSuccess() throws Exception {
    PersonInsertDTO personRequest = new PersonInsertDTO("Augusto Oliveira", null, null, null, null);

    when(pService.insertPerson(any(PersonInsertDTO.class)))
        .thenReturn(new Person.Builder().setName(personRequest.name()).build());

    mockMvc.perform(post("/api/persons")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(personRequest)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.body.name").value("Augusto Oliveira"));
  }

  @Test
  void insertPersonError() throws Exception {
    PersonInsertDTO personRequestWithoutName = new PersonInsertDTO(null, null, null,
        null, null);

    mockMvc.perform(post("/api/persons")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(personRequestWithoutName)))
        .andExpect(status().isBadRequest());
  }

}
