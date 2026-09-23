package com.energy.energy_management_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity 
public class Building {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id; 

  @NotBlank 
  @Size(min=3, max=30)
  private String name;

  private String address;
  private boolean active = true;
  
  public boolean isActive() {
    return active;
  }


  public void setActive(boolean active) {
    this.active = active;
  }

  @ManyToOne 
  private Organization organization;

  public Organization getOrganization() {
    return organization;
  }


  public void setOrganization(Organization organization) {
    this.organization = organization;
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

}
