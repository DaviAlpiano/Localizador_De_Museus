package com.betrybe.museumfinder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Invalid coordinate exception.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCoordinateException extends RuntimeException {

  /**
   * Instantiates a new Invalid coordinate exception.
   *
   * @param message the message
   */
  public InvalidCoordinateException(String message) {
    super(message);
  }
}
