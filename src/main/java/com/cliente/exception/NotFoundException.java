package com.cliente.exception;

import com.cliente.response.Response;

public class NotFoundException extends RuntimeException {

  private transient final Response response;

  public Response getResponse() {
    return response;
  }

  public NotFoundException(Response response) {
    super();
    this.response = response;
  }

}
