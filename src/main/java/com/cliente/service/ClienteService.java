package com.cliente.service;

import com.cliente.model.Cliente;
import com.cliente.repository.ClienteRepository;
import com.cliente.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.json.DomainObjectReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;


@Service
public class ClienteService {

  private ObjectMapper mapper;

  private DomainObjectReader domainObjectReader;

  @Autowired
  private ClienteRepository clienteRepository;

  public void setClienteRepository(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  public ResponseEntity<Response<Cliente>> insert(Cliente cliente, BindingResult result) {
    Response<Cliente> response = new Response<>();

    if (result.hasErrors()) {
      result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    clienteRepository.save(cliente);
    response.setData(cliente);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }


  public ResponseEntity<Response<Cliente>> update(String id, Cliente cliente, BindingResult result) {

    Response<Cliente> response = new Response<>();

    if (result.hasErrors()) {
      result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    Optional<Cliente> clienteMongo = clienteRepository.findById(id);

    if (!clienteMongo.isPresent()) {
      response.getErrors().add("Usuario não encontrado com o id = " + id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    clienteMongo.get().setNome(cliente.getNome());
    clienteMongo.get().setCpf(cliente.getCpf());
    clienteMongo.get().setDataDeNascimento(cliente.getDataDeNascimento());
    clienteRepository.save(clienteMongo.get());

    response.setData(clienteMongo.get());

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  public ResponseEntity<Response<Cliente>> partialUpdate(String id, Cliente clienteParcial, BindingResult result) {
    Response<Cliente> response = new Response<>();
    Optional<Cliente> clienteMongo = clienteRepository.findById(id);

    if (!clienteMongo.isPresent()) {
      response.getErrors().add("Usuario não encontrado com o id = " + id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

//    Cliente patched = domainObjectReader.read(clienteParcial, clienteMongo.get(), mapper);
    // It's not a good solution to merge manually, do the solution above.
    Cliente patched = new Cliente();
    patched.setId(clienteMongo.get().getId());
    patched.setCpf(clienteParcial.getCpf() != null ? clienteParcial.getCpf() : clienteMongo.get().getCpf());
    patched.setNome(clienteParcial.getNome() != null ? clienteParcial.getNome() : clienteMongo.get().getNome());
    patched.setDataDeNascimento(clienteParcial.getDataDeNascimento() != null ? clienteParcial.getDataDeNascimento() : clienteMongo.get().getDataDeNascimento());
    clienteRepository.save(patched);

    response.setData(patched);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  public ResponseEntity<?> listAll(Pageable pageable) {
    Page<Cliente> todosClientes = clienteRepository.findAll(pageable);

    if (todosClientes.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(todosClientes);
    }

    return ResponseEntity.status(HttpStatus.OK).body(todosClientes);
  }

  public ResponseEntity<Response<Cliente>> get(String id) {
    Response<Cliente> response = new Response<>();
    Optional<Cliente> cliente = clienteRepository.findById(id);

    if (cliente.isPresent()) {
      response.setData(cliente.get());
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
  }

  public ResponseEntity<Response<Cliente>> delete(String id) {
    Response<Cliente> response = new Response<>();
    Optional<Cliente> cliente = clienteRepository.findById(id);

    if (!cliente.isPresent()) {
      response.getErrors().add("Cliente não encontrado com o id = " + id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    response.setData(cliente.get());
    clienteRepository.deleteById(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
  }
}
