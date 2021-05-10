package com.progra3.javaMST.back.application.exceptions;

public class CircularReferenceException extends IllegalArgumentException {

  public CircularReferenceException(String s) {
    super(s);
  }
}
