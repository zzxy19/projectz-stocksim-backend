package com.projectz.stocksimbackend.common.Exception;

public class BadRequestException extends Exception {
  public BadRequestException(String message){
    super(message);
  }
}
