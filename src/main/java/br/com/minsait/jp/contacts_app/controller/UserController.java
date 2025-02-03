package br.com.minsait.jp.contacts_app.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.minsait.jp.contacts_app.common.ApiResponse;
import br.com.minsait.jp.contacts_app.enums.ResponseType;
import br.com.minsait.jp.contacts_app.models.User;
import br.com.minsait.jp.contacts_app.service.UserService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
    ApiResponse<List<User>> response = new ApiResponse<>();

    List<User> users = service.getAllUsers();
    response.setType(ResponseType.SUCCESS);
    response.setBody(users);
    response.setMessage("Lista obtida com sucesso.");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping
  public ResponseEntity<ApiResponse<User>> insertUser(@RequestBody @Valid User user) {
    ApiResponse<User> response = new ApiResponse<>();

    User insertedUser = service.insertUser(user);
    response.setType(ResponseType.SUCCESS);
    response.setBody(insertedUser);
    response.setMessage("Usuário inserido com sucesso.");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping("{id}")
  public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
    ApiResponse<User> response = new ApiResponse<>();

    User updatedUser = service.updateUser(id, user);
    response.setType(ResponseType.SUCCESS);
    response.setBody(updatedUser);
    response.setMessage("Usuário atualizado com sucesso.");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable Long id) {
    ApiResponse<User> response = new ApiResponse<>();

    User deletedUser = service.deleteUser(id);
    response.setType(ResponseType.SUCCESS);
    response.setBody(deletedUser);
    response.setMessage("Usuário deletado com sucesso.");
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
