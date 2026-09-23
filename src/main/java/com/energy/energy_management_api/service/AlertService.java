package com.energy.energy_management_api.service;

import com.energy.energy_management_api.mapper.AlertMapper;
import java.util.List;
import org.springframework.stereotype.Service;

import com.energy.energy_management_api.Alert;
import com.energy.energy_management_api.dto.response.AlertResponse;
import com.energy.energy_management_api.exception.InvalidRequestException;
import com.energy.energy_management_api.exception.ResourceNotFoundException;
import com.energy.energy_management_api.repository.AlertRepository;

@Service 
public class AlertService {
  private final AlertMapper alertMapper;
  private final AlertRepository alertRepository;

  public AlertService(AlertRepository alertRepository, AlertMapper alertMapper){
    this.alertRepository = alertRepository;
    this.alertMapper = alertMapper;
  }
  
  public List<AlertResponse> getAlerts(){
    List<Alert> alerts = alertRepository.findAll();
    return alertMapper.toResponseList(alerts);
  }
  
   public List<AlertResponse> getUnresolvedAlerts(){
    List<Alert> alerts = alertRepository.findByResolvedFalse();
    return alertMapper.toResponseList(alerts);
  }

  public AlertResponse resolveAlert(int id){
    Alert alert = alertRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Alert not found"));
    if(alert.isResolved()){
      throw new InvalidRequestException("Alert is already resolved");
    }
    alert.setResolved(true);
    alertRepository.save(alert);
    return alertMapper.toResponse(alert);
  }
}
