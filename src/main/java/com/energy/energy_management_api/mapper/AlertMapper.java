package com.energy.energy_management_api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.energy.energy_management_api.Alert;
import com.energy.energy_management_api.dto.response.AlertResponse;

@Component
public class AlertMapper {
  public AlertResponse toResponse(Alert alert){
    AlertResponse response = new AlertResponse();
    response.setId(alert.getId());
    response.setDeviceName(alert.getReading().getDevice().getName());
    response.setEnergy(alert.getReading().getEnergy());
    response.setMessage(alert.getMessage());
    response.setResolved(alert.isResolved());
    response.setTimestamp(alert.getTimestamp());
    response.setType(alert.getType());
    return response;
  }
  
  public List<AlertResponse> toResponseList(List<Alert> alerts){
    List<AlertResponse> responses = new ArrayList<>();
    for(Alert alert : alerts){
      AlertResponse response = toResponse(alert);
      responses.add(response);
    }
    return responses; 
  }

}
