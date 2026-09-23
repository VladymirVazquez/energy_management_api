package com.energy.energy_management_api.dto.response;



public class ZoneResponse {
  private int id;
  private String name;
  private String buildingName;

  public String getBuildingName() {
    return buildingName;
  }

  public void setBuildingName(String buildingName) {
    this.buildingName = buildingName;
  }

  public void setId(int id){
    this.id = id;
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
}
