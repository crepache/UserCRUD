package com.cliente.web.v1.controller;

import com.cliente.exception.BadRequestException;
import com.cliente.model.Cliente;
import com.cliente.service.ClienteService;
import com.cliente.response.Response;
import com.cliente.web.v1.transport.V1Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import java.util.ArrayList;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

  @Autowired
  public ClienteService clienteService;

  @GetMapping("{id}")
  public ResponseEntity<Response<V1Cliente>> getCliente(@Valid @PathVariable("id") String id) {
    V1Cliente v1Cliente = clienteService.get(id).toV1Cliente();
    Response response = new Response();
    response.setData(v1Cliente);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping()
  public ResponseEntity<?> listAll(Pageable pageable) {
    Page<Cliente> todosClientes = clienteService.listAll(pageable);

    return ResponseEntity.status(HttpStatus.OK).body(todosClientes);
  }

  @PostMapping
  public ResponseEntity<Response<V1Cliente>> create(@Valid @RequestBody V1Cliente cliente,
      BindingResult result) {
    V1Cliente v1Cliente = clienteService.create(cliente.toCliente(), result).toV1Cliente();
    Response response = new Response();
    response.setData(v1Cliente);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Response<V1Cliente>> update(@PathVariable String id,
      @Valid @RequestBody V1Cliente cliente, BindingResult result) {
    V1Cliente v1Cliente = clienteService.update(id, cliente.toCliente(), result).toV1Cliente();

    Response response = new Response();
    response.setData(v1Cliente);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Response<V1Cliente>> updateParcialCliente(@PathVariable String id,
      @RequestBody V1Cliente clienteParcialUpdate, BindingResult result) {
    V1Cliente v1Cliente = clienteService.partialUpdate(id, clienteParcialUpdate.toCliente(), result).toV1Cliente();
    Response response = new Response();
    response.setData(v1Cliente);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable String id) {
    Response response = new Response();

    if (clienteService.delete(id)) {
      response.getErrors().add("Cliente removido com sucesso!");
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    response.getErrors().add("Houve um problema na remocao do Cliente!");
    throw new BadRequestException(response);
  }
}
