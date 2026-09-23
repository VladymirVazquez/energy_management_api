package com.energy.energy_management_api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EnergyReadingRequest {
  @Positive 
  private int deviceId;
  
  @Positive 
  private double power;
  @Positive 
  private double energy;
  @NotNull 
  private LocalDateTime timestamp;

  public int getDeviceId() {
    return deviceId;
  }
  
  public void setDeviceId(int deviceId) {
    this.deviceId = deviceId;
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
  
}
