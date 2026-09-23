package com.energy.energy_management_api.dto.response;


public class OrganizationResponse { 
  

    private int id;

    public void setId(int id) {
      this.id = id;
    }

    private String name;

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
