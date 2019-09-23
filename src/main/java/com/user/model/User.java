package com.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Document(collection = "user")
public class User {

  @Id
  private String id;

  @Size(max = 255, message = "Campo NOME obrigatorio entre 0 and 255")
  @NotBlank(message = "Campo NOME nao pode ser vazio")
  private String nome;

  @Size(max = 30, message = "Campo CPF obrigatorio entre 0 and 30")
  @NotBlank(message = "Campo CPF nao pode ser vazio")
  private String cpf;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date dataDeNascimento;

  public User() {
  }

  public User(String id,
      @Size(max = 255, message = "Campo NOME obrigatorio entre 0 and 255") @NotBlank(
          message = "Campo NOME nao pode ser vazio") String nome,
      @Size(max = 30, message = "Campo CPF obrigatorio entre 0 and 30") @NotBlank(
          message = "Campo CPF nao pode ser vazio") String cpf,
      @NotBlank(message = "Campo DATA DE NASCIMENTO nao pode ser vazio") Date dataDeNascimento) {
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
