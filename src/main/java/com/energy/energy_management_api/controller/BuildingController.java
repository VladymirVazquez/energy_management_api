package com.energy.energy_management_api.controller;  
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RestController;
import com.energy.energy_management_api.dto.request.BuildingRequest;
import com.energy.energy_management_api.dto.response.BuildingResponse;
import com.energy.energy_management_api.service.BuildingService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
public class BuildingController {
  private BuildingService buildingService;

  public BuildingController(BuildingService buildingService){
    this.buildingService = buildingService;

  }

  @GetMapping("/buildings")
  public ResponseEntity<List<BuildingResponse>> getBuildings() {
      return ResponseEntity.ok(buildingService.getBuildings());
  }

  @PostMapping("/buildings")
  public ResponseEntity<BuildingResponse> saveBuilding(@Valid @RequestBody BuildingRequest buildingRequest) {
    return ResponseEntity.status(201).body(buildingService.saveBuilding(buildingRequest));
  }

  @PutMapping("/buildings/{id}")
  public ResponseEntity<BuildingResponse> putBuilding(@PathVariable int id, @Valid @RequestBody BuildingRequest buildingRequest) {
    return ResponseEntity.ok(buildingService.putBuilding(id, buildingRequest));
  }

  @DeleteMapping("/buildings/{id}")
  public ResponseEntity<Void> deleteBuilding(@PathVariable int id) {
    buildingService.deleteBuilding(id);
    return ResponseEntity.status(204).build(); 
  } 
  

 
  
  
}
