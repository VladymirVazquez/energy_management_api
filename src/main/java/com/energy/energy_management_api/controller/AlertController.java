package com.energy.energy_management_api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.energy.energy_management_api.dto.response.AlertResponse;
import com.energy.energy_management_api.service.AlertService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController 
public class AlertController {
  private final AlertService alertService;

  public AlertController(AlertService alertService){
    this.alertService = alertService;
  }

  @GetMapping("/alerts")
  public ResponseEntity<List<AlertResponse>> getAlerts() {
      return ResponseEntity.ok(alertService.getAlerts());
  }

  @GetMapping("/alerts/unresolved")
   public ResponseEntity<List<AlertResponse>> getUnresolvedAlerts() {
      return ResponseEntity.ok(alertService.getUnresolvedAlerts());
  }

  @PutMapping("/alerts/{id}/resolve")
  public ResponseEntity<AlertResponse> resolveAlert(@PathVariable int id) {
    return ResponseEntity.ok(alertService.resolveAlert(id));
  }
  
  
}
