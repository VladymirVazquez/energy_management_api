package com.energy.energy_management_api.dto.response;

import java.time.LocalDateTime;


public class AlertResponse {
  private int id; 

  private String type;
  
  private String message;
  
  private LocalDateTime timestamp;

  private boolean resolved = false;
  
  private String deviceName;  

  private double energy;
  
  public double getEnergy() {
    return energy;
  }
  public void setEnergy(double energy) {
    this.energy = energy;
  }
  public String getDeviceName() {
    return deviceName;
  }
  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }
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

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
}
