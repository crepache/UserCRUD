package com.cliente.service;

import com.cliente.exception.BadRequestException;
import com.cliente.exception.NotFoundException;
import com.cliente.model.Cliente;
import com.cliente.model.Error;
import com.cliente.repository.ClienteRepository;
import com.cliente.response.Response;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;


@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  public void setClienteRepository(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  public Cliente create(Cliente cliente, BindingResult result) {
    if (result.hasErrors()) {
      Response<Cliente> response = new Response<>();
      result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
      throw new BadRequestException(response);
    }

    clienteRepository.save(cliente);
    return cliente;
  }


  public Cliente update(String id, Cliente cliente, BindingResult result) {
    Response<Cliente> response = new Response<>();

    if (result.hasErrors()) {
      result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
      throw new BadRequestException(response);
    }

    Optional<Cliente> clienteMongo = clienteRepository.findById(id);

    if (!clienteMongo.isPresent()) {
      response.getErrors().add("Cliente não encontrado com o id = " + id);
      throw new NotFoundException(response);
    }

    clienteMongo.get().setNome(cliente.getNome());
    clienteMongo.get().setCpf(cliente.getCpf());
    clienteMongo.get().setDataDeNascimento(cliente.getDataDeNascimento());
    clienteRepository.save(clienteMongo.get());

    return clienteMongo.get();
  }

  public Cliente partialUpdate(String id, Cliente clienteParcial, BindingResult result) {
    Optional<Cliente> clienteMongo = clienteRepository.findById(id);

    if (!clienteMongo.isPresent()) {
      Response<Cliente> response = new Response<>();
      response.getErrors().add("Cliente não encontrado com o id = " + id);
      throw new NotFoundException(response);
    }

//    Cliente patched = domainObjectReader.read(clienteParcial, clienteMongo.get(), mapper);
    // It's not a good solution to merge manually, do the solution above.
    Cliente patched = new Cliente();
    patched.setId(clienteMongo.get().getId());
    patched.setCpf(clienteParcial.getCpf() != null ? clienteParcial.getCpf() : clienteMongo.get().getCpf());
    patched.setNome(clienteParcial.getNome() != null ? clienteParcial.getNome() : clienteMongo.get().getNome());
    patched.setDataDeNascimento(clienteParcial.getDataDeNascimento() != null ? clienteParcial.getDataDeNascimento() : clienteMongo.get().getDataDeNascimento());
    clienteRepository.save(patched);

    return patched;
  }

  public Page<Cliente> listAll(Pageable pageable) {
    Page<Cliente> todosClientes = clienteRepository.findAll(pageable);

    if (todosClientes.isEmpty()) {
      Response response = new Response();
      response.getErrors().add("Nenhum Cliente encontrado");
      throw new NotFoundException(response);
    }

    return todosClientes;
  }

  public Cliente get(String id) {
    Optional<Cliente> cliente = clienteRepository.findById(id);

    if (!cliente.isPresent()) {
      Response<Cliente> response = new Response<>();
      response.getErrors().add("Cliente nao encontrado!");
      throw new NotFoundException(response);
    }

    return cliente.get();
  }

  public Boolean delete(String id) {
    Response<Cliente> response = new Response<>();
    Optional<Cliente> cliente = clienteRepository.findById(id);

    if (!cliente.isPresent()) {
      response.getErrors().add("Cliente não encontrado com o id = " + id);
      throw new NotFoundException(response);
    }

    response.setData(cliente.get());
    clienteRepository.deleteById(id);

    return Boolean.TRUE;
  }
}
