package com.energy.energy_management_api.mapper;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.energy.energy_management_api.Device;
import com.energy.energy_management_api.EnergyReading;
import com.energy.energy_management_api.dto.request.EnergyReadingRequest;
import com.energy.energy_management_api.dto.response.EnergyReadingResponse;

@Service 
public class EnergyReadingMapper {

  public EnergyReading toEntity(EnergyReadingRequest request, Device device){
    EnergyReading reading = new EnergyReading();
    reading.setDevice(device);
    reading.setEnergy(request.getEnergy());
    reading.setPower(request.getPower());
    reading.setTimestamp(request.getTimestamp());
    return reading;
  }

  public EnergyReadingResponse toResponse(EnergyReading reading){
    EnergyReadingResponse response = new EnergyReadingResponse();
    response.setId(reading.getId());
    response.setEnergy(reading.getEnergy());
    response.setPower(reading.getPower());
    response.setDeviceName(reading.getDevice().getName());
    response.setTimestamp(reading.getTimestamp());
    return response;
  }

  public List<EnergyReadingResponse> toResponseList(List<EnergyReading> readings) {
    List<EnergyReadingResponse> responses = new ArrayList<>();
    for(EnergyReading reading : readings){
      responses.add(toResponse(reading));
    }
    return responses;
  }

}
