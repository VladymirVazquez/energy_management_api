package com.energy.energy_management_api.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BuildingRequest { 
  @NotBlank 
  @Size(min=3, max=30)
  private String name;
  private String address;
  @Positive 
  private int organizationId;

  public int getOrganizationId() {
    return organizationId;
  }


  public void setOrganizationId(int organizationId) {
    this.organizationId = organizationId;
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

}
