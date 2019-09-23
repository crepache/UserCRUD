package com.user.controller;

import com.user.model.User;
import com.user.response.Response;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  public UserService userService;

  @GetMapping("{id}")
  public ResponseEntity<Response<User>> getUser(@Valid @PathVariable("id") String id) {
    return userService.listUser(id);
  }

  @GetMapping()
  public ResponseEntity<Response<List<User>>> listAll() {
    return userService.listAll();
  }

  @PostMapping
  public ResponseEntity<Response<User>> create(@Valid @RequestBody User user,
      BindingResult result) {
    ResponseEntity<Response<User>> userCreated = userService.insert(user, result);
    return userCreated;
  }

  @PutMapping("/{id}")
  public ResponseEntity<Response<User>> update(@PathVariable String id,
      @Valid @RequestBody User user, BindingResult result) {
    return userService.update(id, user, result);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    userService.delete(id);
  }
}
