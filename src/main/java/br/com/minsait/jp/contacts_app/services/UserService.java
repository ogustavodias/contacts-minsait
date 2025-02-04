package br.com.minsait.jp.contacts_app.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.minsait.jp.contacts_app.dto.UserUpdateDTO;
import br.com.minsait.jp.contacts_app.models.User;
import br.com.minsait.jp.contacts_app.repositorys.UserRepository;
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

  public User updateUser(Long id, UserUpdateDTO user) {
    logger.info("Searching user with id {} to update...", id);
    User userToUpdate = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Usuário de id " + id + " não encontrado."));

    return repository.save(user.toPersistEntity(userToUpdate));
  }

  public User deleteUser(Long id) {
    logger.info("Searching user with id {} to delete...", id);
    User userToDelete = repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

    repository.delete(userToDelete);
    return userToDelete;
  }

}
