package com.energy.energy_management_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.energy.energy_management_api.Organization;



public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
  
  List<Organization> findByActiveTrue();
}
