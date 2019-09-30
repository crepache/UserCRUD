package com.cliente.web.v1.controller;

import com.cliente.model.Cliente;
import com.cliente.service.ClienteService;
import com.cliente.response.Response;
import com.cliente.web.v1.transport.V1Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

  @Autowired
  public ClienteService clienteService;

  @GetMapping("{id}")
  public ResponseEntity<Response<Cliente>> getCliente(@Valid @PathVariable("id") String id) {
    return clienteService.get(id);
  }

  @GetMapping()
  public ResponseEntity<?> listAll(Pageable pageable) {
    return clienteService.listAll(pageable);
  }

  @PostMapping
  public ResponseEntity<Response<Cliente>> create(@Valid @RequestBody V1Cliente v1Cliente,
      BindingResult result) {
    ResponseEntity<Response<Cliente>> clientePersistido = clienteService.insert(v1Cliente.toCliente(), result);

    return clientePersistido;
  }

  @PutMapping("/{id}")
  public ResponseEntity<Response<Cliente>> update(@PathVariable String id,
      @Valid @RequestBody V1Cliente v1Cliente, BindingResult result) {
    return clienteService.update(id, v1Cliente.toCliente(), result);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Response<Cliente>> updateParcialCliente(@PathVariable String id,
      @RequestBody V1Cliente clienteParcialUpdate, BindingResult result) {
    return clienteService.partialUpdate(id, clienteParcialUpdate.toCliente(), result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable String id) {
    return clienteService.delete(id);
  }
}
