package com.energy.energy_management_api.dto.response;

public class BuildingResponse { 
  private int id;
  private String name;
  private String address;
  private String organizationName;

  


  public String getOrganizationName() {
    return organizationName;
  }


  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }


  public String getAddress() {
    return address;
  }


  public void setAddress(String address) {
    this.address = address;
  }

  
  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }

    public int getId() {
    return id;
  }


  public void setId(int id) {
    this.id = id;
  }



}


