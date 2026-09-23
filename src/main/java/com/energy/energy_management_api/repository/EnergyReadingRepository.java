package com.energy.energy_management_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.energy.energy_management_api.Device;
import com.energy.energy_management_api.EnergyReading;

import java.time.LocalDateTime;
import java.util.List;


public interface  EnergyReadingRepository extends JpaRepository<EnergyReading, Integer>{
  List<EnergyReading> findByDevice(Device device);

  List<EnergyReading> findByDeviceAndTimestampBetween(Device device, LocalDateTime localDateTime, LocalDateTime localDateTime2 );

}
