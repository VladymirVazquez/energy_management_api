package com.energy.energy_management_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.energy.energy_management_api.Device;
import com.energy.energy_management_api.Zone;

public interface DeviceRepository extends JpaRepository<Device, Integer>{
  List<Device> findByZone(Zone zone);

  List<Device> findByActiveTrue();
  
  List<Device> findByZoneAndActiveTrue(Zone zone);
}
