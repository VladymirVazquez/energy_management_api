package com.energy.energy_management_api.exception;



public class InvalidRequestException extends RuntimeException{
  

  public InvalidRequestException(String message){
    super(message);
  }
  
}