package com.cliente.exception;

import com.cliente.model.Error;
import com.cliente.response.Response;

public class BadRequestException extends RuntimeException {
  /**
   * Represents an error for an entity.
   */
  private transient final Response response;

  public Response getResponse() {
    return response;
  }

  public BadRequestException(Response response) {
    super();
    this.response = response;
  }

}
