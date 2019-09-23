package com.user.service;

import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public ResponseEntity<Response<User>> insert(User user, BindingResult result) {
    Response<User> response = new Response<>();

    if (result.hasErrors()) {
      result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    userRepository.save(user);
    response.setData(user);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }


  public ResponseEntity<Response<User>> update(String id, User user, BindingResult result) {

    Response<User> response = new Response<>();

    if (result.hasErrors()) {
      result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    Optional<User> userMongo = userRepository.findById(id);

    if (!userMongo.isPresent()) {
      response.getErrors().add("Pessoa não encontra com o id = " + id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    userMongo.get().setNome(user.getNome());
    userMongo.get().setCpf(user.getCpf());
    userMongo.get().setDataDeNascimento(user.getDataDeNascimento());
    userRepository.save(userMongo.get());

    response.setData(userMongo.get());

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  public ResponseEntity<Response<List<User>>> listAll() {
    Response<List<User>> response = new Response<>();
    List<User> userList = userRepository.findAll();
    response.setData(userList);

    if (response.getData().isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  public ResponseEntity<Response<User>> listUser(String id) {

    Response<User> response = new Response<>();
    Optional<User> user = userRepository.findById(id);

    if (user.isPresent()) {
      response.setData(user.get());
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
  }

  public void delete(String id) {
    userRepository.deleteById(id);
  }
}
