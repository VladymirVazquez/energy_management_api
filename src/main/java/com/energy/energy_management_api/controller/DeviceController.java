package com.energy.energy_management_api.controller;

import org.springframework.web.bind.annotation.RestController;
import com.energy.energy_management_api.dto.request.DeviceRequest;
import com.energy.energy_management_api.dto.response.DeviceResponse;
import com.energy.energy_management_api.service.DeviceService;

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
public class DeviceController{
  private final DeviceService deviceService;
  
  public DeviceController(DeviceService deviceService){
    this.deviceService = deviceService;
  }

  @GetMapping("/devices")
  public ResponseEntity<List<DeviceResponse>> getDevices() {
      return ResponseEntity.ok(deviceService.getDevices());
  }

  @PostMapping("/devices")
  public ResponseEntity<DeviceResponse> saveDevice(@Valid @RequestBody DeviceRequest deviceRequest) {
      return ResponseEntity.status(201).body(deviceService.saveDevice(deviceRequest));
  }
  
  @PutMapping("/devices/{id}")
  public ResponseEntity<DeviceResponse> putDevice(@PathVariable int id, @Valid @RequestBody DeviceRequest deviceRequest) {
    return ResponseEntity.ok(deviceService.putDevice(id, deviceRequest));

  }

  @DeleteMapping("/devices/{id}")
  public ResponseEntity<Void> deleteDevice(@PathVariable int id){
    deviceService.deleteDevice(id);
    return ResponseEntity.status(204).build();
  }
}