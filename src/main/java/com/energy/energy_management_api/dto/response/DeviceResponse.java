package com.energy.energy_management_api.dto.response;

public class DeviceResponse {
  private int id;
  public void setId(int id) {
    this.id = id;
  }

  private String name;
  private String type;
  private String zoneName;
  private double maxEnergy; 

  public String getZoneName() {
    return zoneName;
  }

  public void setZoneName(String zoneName) {
    this.zoneName = zoneName;
  }


  public double getMaxEnergy() {
    return maxEnergy;
  }

  public void setMaxEnergy(double maxEnergy) {
    this.maxEnergy = maxEnergy;
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
