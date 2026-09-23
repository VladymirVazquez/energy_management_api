package com.energy.energy_management_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.energy.energy_management_api.Organization;
import com.energy.energy_management_api.dto.request.OrganizationRequest;
import com.energy.energy_management_api.dto.response.OrganizationResponse;
import com.energy.energy_management_api.exception.ResourceNotFoundException;
import com.energy.energy_management_api.repository.OrganizationRepository;

@Service
public class OrganizationService {
  private final OrganizationRepository organizationRepository;

  public OrganizationService(OrganizationRepository organizationRepository){
    this.organizationRepository = organizationRepository;
  }

  public OrganizationResponse saveOrganization(OrganizationRequest organizationRequest){
    Organization organization = new Organization();
    organization.setName(organizationRequest.getName());
    organizationRepository.save(organization);
    OrganizationResponse response = new OrganizationResponse();
    response.setId(organization.getId());
    response.setName(organization.getName());
    return response;
  }

  public List<OrganizationResponse> getOrganizations(){
    List<Organization> organizations = organizationRepository.findByActiveTrue();
    List<OrganizationResponse> responses = new ArrayList<>();
    for(Organization organization : organizations){
      OrganizationResponse response = new OrganizationResponse();
      response.setId(organization.getId());
      response.setName(organization.getName());
      responses.add(response);
    }
    return responses;
  }

  public OrganizationResponse putOrganization(int id, OrganizationRequest organizationRequest){
    Organization organization = organizationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Organization not found"));
    if(!organization.isActive()){
      throw new ResourceNotFoundException("Organization not found");
    }
    organization.setName(organizationRequest.getName());
    organizationRepository.save(organization);
    OrganizationResponse response = new OrganizationResponse();
    response.setId(organization.getId());
    response.setName(organization.getName());
    return response;
  }

  public void deleteOrganization(int id){
    Organization organization = organizationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Organization not found"));
    if(!organization.isActive()){
      throw new ResourceNotFoundException("Organization not found");
    }
    organization.setActive(false);
    organizationRepository.save(organization);
  }

  
}
