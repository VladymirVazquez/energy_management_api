package com.energy.energy_management_api;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity 
public class Alert {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id; 
  @OneToOne 
  private EnergyReading reading;
  
  private String type;
  
  private String message;
  
  private LocalDateTime timestamp;

  private boolean resolved = false;
  
  public boolean isResolved() {
    return resolved;
  }
  public void setResolved(boolean resolved) {
    this.resolved = resolved;
  }
  public LocalDateTime getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  
  public EnergyReading getReading() {
    return reading;
  }
  public void setReading(EnergyReading reading) {
    this.reading = reading;
  }

  public int getId() {
    return id;
  }

}
