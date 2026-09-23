package com.energy.energy_management_api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.energy.energy_management_api.dto.request.OrganizationRequest;
import com.energy.energy_management_api.dto.response.OrganizationResponse;
import com.energy.energy_management_api.service.OrganizationService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController 
public class OrganizationController {
  private final OrganizationService organizationService;

  public OrganizationController(OrganizationService organizationService){
    this.organizationService = organizationService;
  }

  @GetMapping("/organizations")
  public ResponseEntity<List<OrganizationResponse>> getOrganizations() {
      return ResponseEntity.ok(organizationService.getOrganizations());
  }
  

  @PostMapping("/organizations")
  public ResponseEntity<OrganizationResponse> saveOrganization(@Valid @RequestBody OrganizationRequest organizationRequest) {
      return ResponseEntity.status(201).body(organizationService.saveOrganization(organizationRequest));
  }

  @DeleteMapping("/organizations/{id}")
  public ResponseEntity<Void> deleteOrganization( @PathVariable int id){
    organizationService.deleteOrganization(id);
    return ResponseEntity.status(204).build();
  }

  @PutMapping("/organizations/{id}")
  public ResponseEntity<OrganizationResponse> putOrganization( @PathVariable int id ,@Valid @RequestBody OrganizationRequest organizationRequest){
    return ResponseEntity.ok(organizationService.putOrganization(id, organizationRequest));
  }
  


}
