package br.com.minsait.jp.contacts_app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.minsait.jp.contacts_app.models.User;
import br.com.minsait.jp.contacts_app.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserRepository repository;

  public List<User> getAllUsers() {
    logger.info("Searching all users...");
    return repository.findAll();
  }

  public User insertUser(User user) {
    logger.info("Entering user {}...", user);
    return repository.save(user);
  }

  public User updateUser(Long id, User user) {
    logger.info("Searching user with id {} to update...", id);
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
    logger.info("Searching user with id {} to delete...", id);
    User userToDelete = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

    repository.delete(userToDelete);
    return userToDelete;
  }

}
