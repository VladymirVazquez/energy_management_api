package com.energy.energy_management_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.energy.energy_management_api.Alert;

public interface AlertRepository extends JpaRepository<Alert ,Integer>{
  List<Alert> findByResolvedFalse();
}
