package com.energy.energy_management_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity 
public class Zone {
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank 
  @Size(min=2, max=30)
  private String name;
  private boolean active = true;

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @ManyToOne 
  private Building building;
  
  
  public Building getBuilding() {
    return building;
  }

  public void setBuilding(Building building) {
    this.building = building;
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
