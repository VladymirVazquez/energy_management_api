package com.energy.energy_management_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity 
public class Device {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
 
  @NotBlank 
  @Size(min= 3, max = 40)
  private String name;

  private String type;

  @ManyToOne 
  private Zone zone;

  @Positive 
  private double maxEnergy; 
  private boolean active = true;


  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public double getMaxEnergy() {
    return maxEnergy;
  }

  public void setMaxEnergy(double maxEnergy) {
    this.maxEnergy = maxEnergy;
  }

  public Zone getZone() {
    return zone;
  }

  public void setZone(Zone zone) {
    this.zone = zone;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
