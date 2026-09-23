package com.energy.energy_management_api.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ZoneRequest {

  @NotBlank 
  @Size(min=2, max=30)
  private String name;
  @Positive 
  private int buildingId;
  


  public int getBuildingId() {
    return buildingId;
  }

  public void setBuildingId(int buildingId) {
    this.buildingId = buildingId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
