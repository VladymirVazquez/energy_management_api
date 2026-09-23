package com.energy.energy_management_api;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity 
public class EnergyReading {
  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private int id;
  @ManyToOne 
  private Device device;
  @Positive 
  private double power;
  @Positive 
  private double energy;
  @NotNull 
  private LocalDateTime timestamp;

   
  public int getId() {
    return id;
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
  
  public Device getDevice() {
    return device;
  }
  public void setDevice(Device device) {
    this.device = device;
  }
  
}
