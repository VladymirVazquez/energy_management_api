package com.energy.energy_management_api.service;

import com.energy.energy_management_api.mapper.BuildingMapper;
import com.energy.energy_management_api.repository.OrganizationRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.energy.energy_management_api.Building;
import com.energy.energy_management_api.Organization;
import com.energy.energy_management_api.dto.request.BuildingRequest;
import com.energy.energy_management_api.dto.response.BuildingResponse;
import com.energy.energy_management_api.exception.ResourceNotFoundException;
import com.energy.energy_management_api.repository.BuildingRepository;

@Service 
public class BuildingService {
  private final BuildingMapper buildingMapper;
  private final OrganizationRepository organizationRepository;
  private final BuildingRepository buildingRepository;

  public BuildingService(BuildingRepository buildingRepository, OrganizationRepository organizationRepository, BuildingMapper buildingMapper){
    this.buildingRepository = buildingRepository;
    this.organizationRepository = organizationRepository;
    this.buildingMapper = buildingMapper;
  }

  public BuildingResponse saveBuilding(BuildingRequest buildingRequest){
    Organization organization = organizationRepository.findById(buildingRequest.getOrganizationId()).orElseThrow(() -> new ResourceNotFoundException("Organization not found"));
    if(!organization.isActive()){
      throw new ResourceNotFoundException("Organization not found");
    }
    Building building = new Building();
    building.setName(buildingRequest.getName());
    building.setAddress(buildingRequest.getAddress());
    building.setOrganization(organization);
    buildingRepository.save(building);
    BuildingResponse response = new BuildingResponse();
    response.setId(building.getId());
    response.setName(building.getName());
    response.setAddress(building.getAddress());
    response.setOrganizationName(building.getOrganization().getName());
    return response;
  }

  public List<BuildingResponse> getBuildings(){
    List<Building> buildings = buildingRepository.findByActiveTrue();
    List<BuildingResponse> responses = new ArrayList<>();
    for(Building building : buildings){
      BuildingResponse response = new BuildingResponse();
      response.setId(building.getId());
      response.setName(building.getName());
      response.setOrganizationName(building.getOrganization().getName());
      response.setAddress(building.getAddress());
      responses.add(response);
    }
    return responses;

  }

  public void deleteBuilding(int id){
    Building building = buildingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Building not found"));
    if(!building.isActive()){
      throw new ResourceNotFoundException("Building not found");
    }
    building.setActive(false);
    buildingRepository.save(building);
  }

  public BuildingResponse putBuilding(int id, BuildingRequest buildingRequest){
    Building building = buildingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Building not found"));
    if(!building.isActive()){
      throw new ResourceNotFoundException("Building not found");
    }
    Organization organization = organizationRepository.findById(buildingRequest.getOrganizationId()).orElseThrow(() -> new ResourceNotFoundException("Organization not found"));
    if(!organization.isActive()){
      throw new ResourceNotFoundException("Organization not found");
    }
    building.setName(buildingRequest.getName());
    building.setAddress(buildingRequest.getAddress());
    building.setOrganization(organization);
    buildingRepository.save(building);
    return buildingMapper.toResponse(building);
   }
}
