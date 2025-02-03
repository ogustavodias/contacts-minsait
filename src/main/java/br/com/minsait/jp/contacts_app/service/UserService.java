package br.com.minsait.jp.contacts_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.minsait.jp.contacts_app.models.User;
import br.com.minsait.jp.contacts_app.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  public List<User> getAllUsers() {
    return repository.findAll();
  }

  public User insertUser(User user) {
    return repository.save(user);
  }

  public User updateUser(Long id, User user) {
    User userToUpdate = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Usuário de id " + id + " não encontrado"));

    if (user.getName() != null) {
      if (user.getName().trim().isEmpty())
        throw new IllegalArgumentException("'name' não pode ser em branco");

      userToUpdate.setName(user.getName().trim());
    }

    if (user.getNickname() != null) {
      if (user.getNickname().trim().isEmpty())
        throw new IllegalArgumentException("'nickname' não pode ser em branco");

      userToUpdate.setNickname(user.getNickname().trim());
    }

    return repository.save(userToUpdate);
  }

  public User deleteUser(Long id) {
    User userToDelete = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

    repository.delete(userToDelete);
    return userToDelete;
  }

}
