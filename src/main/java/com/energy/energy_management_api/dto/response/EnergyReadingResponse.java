package com.energy.energy_management_api.dto.response;

import java.time.LocalDateTime;


public class EnergyReadingResponse {
  private int id;
  
  private String deviceName;
  private double power;
  private double energy;
  private LocalDateTime timestamp;

  public String getDeviceName() {
    return deviceName;
  }
  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public double getPower() {
    return power;
  }
  public void setPower(double power) {
    this.power = power;
  }
  
  public double getEnergy() {
    return energy;
  }
  public void setEnergy(double energy) {
    this.energy = energy;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }
  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
  

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  
}
