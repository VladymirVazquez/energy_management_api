package com.energy.energy_management_api.service;

import com.energy.energy_management_api.mapper.ZoneMapper;
import com.energy.energy_management_api.repository.BuildingRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.energy.energy_management_api.Building;
import com.energy.energy_management_api.Zone;
import com.energy.energy_management_api.dto.request.ZoneRequest;
import com.energy.energy_management_api.dto.response.ZoneResponse;
import com.energy.energy_management_api.exception.ResourceNotFoundException;
import com.energy.energy_management_api.repository.ZoneRepository;

@Service 
public class ZoneService{
  private final ZoneMapper zoneMapper;
  private final BuildingRepository buildingRepository;
  private final ZoneRepository zoneRepository;

  public ZoneService (ZoneRepository zoneRepository, BuildingRepository buildingRepository, ZoneMapper zoneMapper){
    this.zoneRepository = zoneRepository;
    this.buildingRepository = buildingRepository;
    this.zoneMapper = zoneMapper;
  }

  public ZoneResponse saveZone(ZoneRequest zoneRequest){
    Building building = buildingRepository.findById(zoneRequest.getBuildingId()).orElseThrow(() -> new ResourceNotFoundException("Building not found"));
    if(!building.isActive()){
      throw new ResourceNotFoundException("Building not found");
    }
    Zone zone = new Zone();
    zone.setName(zoneRequest.getName());
    zone.setBuilding(building);
    zoneRepository.save(zone);
    ZoneResponse response = new ZoneResponse();
    response.setBuildingName(zone.getBuilding().getName());
    response.setId(zone.getId());
    response.setName(zone.getName());
    return response;
  }

  public List<ZoneResponse> getZones(){
    List<Zone> zones = zoneRepository.findByActiveTrue();
    List<ZoneResponse> responses = new ArrayList<>();
    for(Zone zone : zones){
      ZoneResponse response = new ZoneResponse();
      response.setId(zone.getId());
      response.setName(zone.getName());
      response.setBuildingName(zone.getBuilding().getName());
      responses.add(response);
    }
    return responses;
  }

  public ZoneResponse putZone(int id, ZoneRequest zoneRequest){
    Zone zone = zoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
    if(!zone.isActive()){
      throw new ResourceNotFoundException("Zone not found");
    }
    Building building = buildingRepository.findById(zoneRequest.getBuildingId()).orElseThrow(() -> new ResourceNotFoundException("Building not found"));
    if(!building.isActive()){
      throw new ResourceNotFoundException("Building not found");
    }
    zone.setName(zoneRequest.getName());
    zone.setBuilding(building);
    zoneRepository.save(zone);
    return zoneMapper.toResponse(zone);
  }

  public void deleteZone(int id){
    Zone zone = zoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
    if (!zone.isActive()) {
      throw new ResourceNotFoundException("Zone not found");
    }
    zone.setActive(false);
    zoneRepository.save(zone);
  }
}