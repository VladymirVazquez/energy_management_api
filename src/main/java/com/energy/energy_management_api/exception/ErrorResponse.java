package com.energy.energy_management_api.exception;

import java.time.LocalDateTime;

public class ErrorResponse{
  private int status;
  private LocalDateTime timestamp;
  private String message;

  public int getStatus() {
    return status;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  
  public LocalDateTime getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public ErrorResponse(int status, String message, LocalDateTime timestamp ){
    this.status  = status;
    this.message = message;
    this.timestamp = timestamp;
    
  }
}