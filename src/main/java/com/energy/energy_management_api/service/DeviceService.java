package com.energy.energy_management_api.service;

import com.energy.energy_management_api.mapper.DeviceMapper;
import java.util.List;

import org.springframework.stereotype.Service;

import com.energy.energy_management_api.Device;
import com.energy.energy_management_api.Zone;
import com.energy.energy_management_api.dto.request.DeviceRequest;
import com.energy.energy_management_api.dto.response.DeviceResponse;
import com.energy.energy_management_api.exception.ResourceNotFoundException;
import com.energy.energy_management_api.repository.DeviceRepository;
import com.energy.energy_management_api.repository.ZoneRepository;

@Service
public class DeviceService {
  private final DeviceMapper deviceMapper;
  private final DeviceRepository deviceRepository;
  private final ZoneRepository zoneRepository;
  
  public DeviceService(DeviceRepository deviceRepository, ZoneRepository zoneRepository, DeviceMapper deviceMapper){
    this.deviceRepository = deviceRepository;
    this.zoneRepository = zoneRepository;
    this.deviceMapper = deviceMapper;
  }

  public List<DeviceResponse> getDevices(){
    List<Device> devices = deviceRepository.findByActiveTrue();
    List<DeviceResponse> responses = deviceMapper.toResponseList(devices);
    return responses;
  }

  public DeviceResponse saveDevice(DeviceRequest deviceRequest){
    Zone zone = zoneRepository.findById(deviceRequest.getZoneId()).orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
    if(!zone.isActive()){
      throw new ResourceNotFoundException("Zone not found");
    }
    Device device = new Device();
    device.setName(deviceRequest.getName());
    device.setType(deviceRequest.getType());
    device.setMaxEnergy(deviceRequest.getMaxEnergy());
    device.setZone(zone);
    deviceRepository.save(device);
    DeviceResponse response = deviceMapper.toResponse(device);

    return response;
  }

  public DeviceResponse putDevice(int id, DeviceRequest deviceRequest){
    Device device = deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
    if(!device.isActive()){
      throw new ResourceNotFoundException("Device not found");
    }
    Zone zone = zoneRepository.findById(deviceRequest.getZoneId()).orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
    if(!zone.isActive()){
      throw new ResourceNotFoundException("Zone not found");
    }
    device.setName(deviceRequest.getName());
    device.setType(deviceRequest.getType());
    device.setZone(zone);
    device.setMaxEnergy(deviceRequest.getMaxEnergy());
    deviceRepository.save(device);
    return deviceMapper.toResponse(device);
  }

  public void deleteDevice(int id){
    Device device = deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
    if(!device.isActive()){
      throw new ResourceNotFoundException("Device not found");
    }
    device.setActive(false);
    deviceRepository.save(device);
  }
}