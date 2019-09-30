package com.cliente.web.v1.transport;

import com.cliente.model.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.validation.constraints.Size;

public class V1Cliente {

  private String id;

  @Size(max = 255, message = "Campo NOME obrigatorio entre 0 and 255")
  private String nome;

  @Size(max = 30, message = "Campo CPF obrigatorio entre 0 and 30")
  private String cpf;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date dataDeNascimento;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Date getDataDeNascimento() {
    return dataDeNascimento;
  }

  public void setDataDeNascimento(Date dataDeNascimento) {
    this.dataDeNascimento = dataDeNascimento;
  }

  public Cliente toCliente() {
    return new ModelMapper().map(this, Cliente.class);
  }

}
