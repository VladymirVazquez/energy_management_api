package com.energy.energy_management_api.controller;
import com.energy.energy_management_api.mapper.BuildingMapper;
import com.energy.energy_management_api.mapper.DeviceMapper;
import com.energy.energy_management_api.mapper.EnergyReadingMapper;
import com.energy.energy_management_api.mapper.ZoneMapper;
import org.springframework.web.bind.annotation.RestController;

import com.energy.energy_management_api.Building;
import com.energy.energy_management_api.Device;
import com.energy.energy_management_api.EnergyReading;
import com.energy.energy_management_api.Zone;
import com.energy.energy_management_api.dto.request.EnergyReadingRequest;
import com.energy.energy_management_api.dto.response.BuildingResponse;
import com.energy.energy_management_api.dto.response.DeviceResponse;
import com.energy.energy_management_api.dto.response.EnergyReadingResponse;
import com.energy.energy_management_api.dto.response.ZoneResponse;
import com.energy.energy_management_api.service.EnergyReadingService;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
public class EnergyReadingController {
  private final BuildingMapper buildingMapper;
  private final ZoneMapper zoneMapper;
  private final DeviceMapper deviceMapper;
  private final EnergyReadingMapper energyReadingMapper;
  private final EnergyReadingService energyReadingService;

  public EnergyReadingController(EnergyReadingService energyReadingService, EnergyReadingMapper energyReadingMapper, DeviceMapper deviceMapper, ZoneMapper zoneMapper, BuildingMapper buildingMapper){
    this.energyReadingService = energyReadingService;
    this.energyReadingMapper = energyReadingMapper;
    this.deviceMapper = deviceMapper;
    this.zoneMapper = zoneMapper;
    this.buildingMapper = buildingMapper;
  }

  @GetMapping("/readings")
  public ResponseEntity<List<EnergyReadingResponse>> getReadings() {
      return ResponseEntity.ok(energyReadingService.getReadings());
  }

  @PostMapping("/readings")
  public ResponseEntity<EnergyReadingResponse> saveReading(@Valid @RequestBody EnergyReadingRequest energyReadingRequest) {
    return ResponseEntity.status(201).body(energyReadingService.saveReading(energyReadingRequest));
  }

  @GetMapping("/devices/{id}/readings")
  public ResponseEntity<List<EnergyReadingResponse>> findReadingsByDevice(@PathVariable int id, 
    @RequestParam(value = "startDate", required = false) LocalDateTime startDate, 
    @RequestParam(value = "finalDate", required = false) LocalDateTime finalDate) {

      return ResponseEntity.ok(energyReadingService.getReadingsByDevice(id, startDate, finalDate));
        
    
  }

  @GetMapping("/devices/{id}/consumption")
  public ResponseEntity<Double> getTotalEnergyByDevice(@PathVariable int id, 
    @RequestParam(value = "startDate", required = false) LocalDateTime startDate, 
    @RequestParam(value = "finalDate", required = false) LocalDateTime finalDate) {

    return ResponseEntity.ok(energyReadingService.getTotalEnergyByDevice(id, startDate, finalDate));
  
  }

  @GetMapping("/zones/{id}/consumption")
  public ResponseEntity<Double> getTotalEnergyByZone(@PathVariable int id, 
    @RequestParam(value = "startDate", required = false) LocalDateTime startDate, 
    @RequestParam(value = "finalDate", required = false) LocalDateTime finalDate) {

    return ResponseEntity.ok(energyReadingService.getTotalEnergyByZone(id, startDate, finalDate));
     
  }

  @GetMapping("/buildings/{id}/consumption")
  public ResponseEntity<Double> getTotalEnergyByBuilding(@PathVariable int id, 
    @RequestParam(value = "startDate", required = false) LocalDateTime startDate, 
    @RequestParam(value = "finalDate", required = false) LocalDateTime finalDate) {
      return ResponseEntity.ok(energyReadingService.getTotalEnergyByBuilding(id , startDate, finalDate));
  }

  @GetMapping("/readings/{id}/abnormal")
  public ResponseEntity<Boolean> isAbnormal(@PathVariable int id) {
      return ResponseEntity.ok(energyReadingService.isAbnormal(id));
  }
  
  @GetMapping("/devices/{id}/average")
  public ResponseEntity<Double> getAverageEnergyByDevice(@PathVariable int id) {
      return ResponseEntity.ok(energyReadingService.getAverageEnergyByDevice(id));
  }

  @GetMapping("/devices/{id}/max")
  public ResponseEntity<Double> getMaxEnergyByDevice(@PathVariable int id) {
      return ResponseEntity.ok(energyReadingService.getMaxEnergyByDevice(id));
  }

  @GetMapping("/devices/{id}/min")
  public ResponseEntity<Double> getMinEnergyByDevice(@PathVariable int id) {
      return ResponseEntity.ok(energyReadingService.getMinEnergyByDevice(id));
  }

  @GetMapping("/devices/{id}/abnormal")
  public ResponseEntity<List<EnergyReadingResponse>> getAbnormalReadingsByDevice(@PathVariable int id) {
    List<EnergyReading> readings = energyReadingService.getAbnormalReadingsByDevice(id);
    return ResponseEntity.ok(energyReadingMapper.toResponseList(readings));
  }

  @GetMapping("/zones/{id}/devices")
  public ResponseEntity<List<DeviceResponse>> getDevicesByZone(@PathVariable int id) {
    List<Device> devices = energyReadingService.getDevicesByZone(id);
      return ResponseEntity.ok(deviceMapper.toResponseList(devices));
  }

  @GetMapping("/buildings/{id}/zones")
  public ResponseEntity<List<ZoneResponse>> getZonesByBuilding(@PathVariable int id) {
    List<Zone> zones =  energyReadingService.getZonesByBuilding(id);
      return ResponseEntity.ok(zoneMapper.toResponseList(zones));
  }

  @GetMapping("/zones/{id}/abnormal")
  public ResponseEntity<List<EnergyReadingResponse>> getAbnormalReadingsByZone(@PathVariable int id) {
    List<EnergyReading> readings = energyReadingService.getAbnormalReadingsByZone(id);
    return ResponseEntity.ok(energyReadingMapper.toResponseList(readings));
  }

  @GetMapping("/organizations/{id}/buildings")
  public ResponseEntity<List<BuildingResponse>> getBuildingsByOrganization(@PathVariable int id) {
    List<Building> buildings = energyReadingService.getBuildingsByOrganization(id);
      return ResponseEntity.ok(buildingMapper.toResponseList(buildings));
  }

  @GetMapping("/organizations/{id}/consumption")
  public ResponseEntity<Double> getTotalEnergyByOrganization(@PathVariable int id,  
    @RequestParam(value = "startDate", required = false) LocalDateTime startDate, 
    @RequestParam(value = "finalDate", required = false) LocalDateTime finalDate) {
      return ResponseEntity.ok(energyReadingService.getTotalEnergyByOrganization(id, startDate, finalDate));
  }

  




  
}
