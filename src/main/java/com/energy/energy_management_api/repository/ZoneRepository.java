package com.energy.energy_management_api.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.energy.energy_management_api.Building;
import com.energy.energy_management_api.Zone;
import java.util.List;



public interface ZoneRepository extends JpaRepository<Zone, Integer>{
  List<Zone> findByBuilding(Building building);
  List<Zone> findByActiveTrue();
  List<Zone> findByBuildingAndActiveTrue(Building building);
}
