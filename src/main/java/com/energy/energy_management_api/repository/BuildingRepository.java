package com.energy.energy_management_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.energy.energy_management_api.Building;
import com.energy.energy_management_api.Organization;

public interface BuildingRepository extends JpaRepository<Building, Integer>{

  List<Building> findByOrganization(Organization organization);

  List<Building> findByActiveTrue();
  
  List<Building> findByOrganizationAndActiveTrue(Organization organization);
}
