package com.energy.energy_management_api.controller;

import org.springframework.web.bind.annotation.RestController;
import com.energy.energy_management_api.dto.request.ZoneRequest;
import com.energy.energy_management_api.dto.response.ZoneResponse;
import com.energy.energy_management_api.service.ZoneService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController 
public class ZoneController {
  private final ZoneService zoneService;

  public ZoneController(ZoneService zoneService){
    this.zoneService = zoneService;
  }

  @GetMapping("/zones")
  public ResponseEntity<List<ZoneResponse>> getZones() {
      return ResponseEntity.ok(zoneService.getZones());
  }

  @PostMapping("/zones")
  public ResponseEntity<ZoneResponse> saveZone(@Valid @RequestBody ZoneRequest zoneRequest) {
      return ResponseEntity.status(201).body(zoneService.saveZone(zoneRequest));
  }

  @PutMapping("/zones/{id}")
  public ResponseEntity<ZoneResponse> putZone(@PathVariable int id,@Valid @RequestBody ZoneRequest zoneRequest) {
      return ResponseEntity.ok(zoneService.putZone(id, zoneRequest));
  }

  @DeleteMapping("/zones/{id}")
  public ResponseEntity<Void> deleteZone(@PathVariable int id) {
    zoneService.deleteZone(id);
      return ResponseEntity.status(204).build();
  }
}
