package com.energy.energy_management_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class DeviceRequest {
  @NotBlank 
  @Size(min= 3, max = 40)
  private String name;
  private String type;
  @Positive 
  private int zoneId;
  @Positive 
  private double maxEnergy; 

  public int getZoneId() {
    return zoneId;
  }

  public void setZoneId(int zoneId) {
    this.zoneId = zoneId;
  }

  public double getMaxEnergy() {
    return maxEnergy;
  }

  public void setMaxEnergy(double maxEnergy) {
    this.maxEnergy = maxEnergy;
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
