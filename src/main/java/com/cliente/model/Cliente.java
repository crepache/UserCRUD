package com.cliente.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection = "cliente")
public class Cliente {

  @Id
  private String id;

  private String nome;

  private String cpf;

  private Date dataDeNascimento;

  public Cliente() {
  }

  public Cliente(String id, String nome, String cpf, Date dataDeNascimento) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.dataDeNascimento = dataDeNascimento;
  }

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
}
