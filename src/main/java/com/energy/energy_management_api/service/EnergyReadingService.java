package com.energy.energy_management_api.service;
import com.energy.energy_management_api.repository.AlertRepository;
import com.energy.energy_management_api.repository.BuildingRepository;
import com.energy.energy_management_api.repository.DeviceRepository;

import com.energy.energy_management_api.repository.OrganizationRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.energy.energy_management_api.Alert;
import com.energy.energy_management_api.Building;
import com.energy.energy_management_api.Device;
import com.energy.energy_management_api.EnergyReading;
import com.energy.energy_management_api.Organization;
import com.energy.energy_management_api.Zone;
import com.energy.energy_management_api.dto.request.EnergyReadingRequest;
import com.energy.energy_management_api.dto.response.EnergyReadingResponse;
import com.energy.energy_management_api.exception.InvalidRequestException;
import com.energy.energy_management_api.exception.ResourceNotFoundException;
import com.energy.energy_management_api.mapper.EnergyReadingMapper;
import com.energy.energy_management_api.repository.EnergyReadingRepository;
import com.energy.energy_management_api.repository.ZoneRepository;

@Service
public class EnergyReadingService {
  private final AlertRepository alertRepository;
  private final OrganizationRepository organizationRepository;
  private final DeviceRepository deviceRepository;
  private final EnergyReadingRepository energyReadingRepository;
  private final ZoneRepository zoneRepository;
  private final BuildingRepository buildingRepository;
  private final EnergyReadingMapper energyReadingMapper;

  public EnergyReadingService(
    EnergyReadingRepository energyReadingRepository, 
    DeviceRepository deviceRepository, 
    ZoneRepository zoneRepository, 
    BuildingRepository buildingRepository, 
    OrganizationRepository organizationRepository, 
    EnergyReadingMapper energyReadingMapper, 
    AlertRepository alertRepository){

    this.energyReadingRepository = energyReadingRepository;
    this.deviceRepository = deviceRepository;
    this.zoneRepository = zoneRepository;
    this.buildingRepository = buildingRepository;
    this.energyReadingMapper = energyReadingMapper;
    this.organizationRepository = organizationRepository;
    this.alertRepository = alertRepository;
  }

  public EnergyReadingResponse saveReading(EnergyReadingRequest energyReadingRequest){
    Device device = deviceRepository.findById(energyReadingRequest.getDeviceId()).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
    if(!device.isActive()){
      throw new ResourceNotFoundException("Device not found");
    }
    EnergyReading reading = energyReadingRepository.save(energyReadingMapper.toEntity(energyReadingRequest, device));
    if(reading.getEnergy() > device.getMaxEnergy()){
      Alert alert = new Alert();
      alert.setReading(reading);
      alert.setTimestamp(reading.getTimestamp());
      alert.setMessage(
        "Energy consumption exceeded the configured limit by "
        + (reading.getEnergy() - reading.getDevice().getMaxEnergy())
        + " kWh. Recorded: "
        + reading.getEnergy()
        + " kWh; limit: "
        + reading.getDevice().getMaxEnergy()
        + " kWh."
        );    
      alert.setType("HIGH_CONSUMPTION");
      alertRepository.save(alert);
    }
    return energyReadingMapper.toResponse(reading);

   
  }

  public List<EnergyReadingResponse> getReadings(){
    List<EnergyReading> readings = energyReadingRepository.findAll();
    return energyReadingMapper.toResponseList(readings);
  }

  public List<EnergyReadingResponse> getReadingsByDevice(int id){
    List<EnergyReading> readings = energyReadingRepository.findByDevice(deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Device not found")) );
    
    return energyReadingMapper.toResponseList(readings);
  }

  public double getTotalEnergyByDevice(int id){
    Device device = deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
    List<EnergyReading> readings = energyReadingRepository.findByDevice(device);
    double totalEnergy = 0.0;
    for(EnergyReading reading :  readings){
      totalEnergy += reading.getEnergy();
    }
    return totalEnergy;
  }

  public double getTotalEnergyByZone(int id){
    Zone zone = zoneRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Zone not found"));
    List<Device> devices = deviceRepository.findByZone(zone);
    double totalEnergy = 0.0;
    for(Device device : devices){
      List<EnergyReading> readings = energyReadingRepository.findByDevice(device);
      for(EnergyReading reading: readings){
        totalEnergy += reading.getEnergy();
      }
    }
    return totalEnergy;
  }
  
  public double getTotalEnergyByBuilding(int id){
    Building building = buildingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Building not found"));
    List<Zone> zones = zoneRepository.findByBuilding(building);
    double totalEnergy = 0.0;
    for(Zone zone : zones){
      totalEnergy += getTotalEnergyByZone(zone.getId());
    }
    return totalEnergy;
  }

  public boolean isAbnormal(int id){
    EnergyReading reading = energyReadingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Reading not found"));
    Double maxEnergy = reading.getDevice().getMaxEnergy();
    return reading.getEnergy() > maxEnergy;
  }

  public Double getAverageEnergyByDevice(int id){
    Device device = deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
    List<EnergyReading> readings = energyReadingRepository.findByDevice(device);
    double totalEnergy = 0.0;
    for(EnergyReading reading :  readings){
      totalEnergy += reading.getEnergy();
    }
    if(readings.isEmpty()){
      return null;
    }
    else{
    return totalEnergy/readings.size();
    }
  }

  public Double getMaxEnergyByDevice(int id){
    Device device = deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
    List<EnergyReading> readings = energyReadingRepository.findByDevice(device);
    if (readings.isEmpty()){
      return null;
    }
    double maxEnergy =  readings.getFirst().getEnergy();
    for(EnergyReading reading :  readings){
      if(maxEnergy < reading.getEnergy()){
        maxEnergy = reading.getEnergy();
      }
    }
    return maxEnergy;

  }

  public Double getMinEnergyByDevice(int id){
    Device device = deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
    List<EnergyReading> readings = energyReadingRepository.findByDevice(device);
    if (readings.isEmpty()){
      return null;
    }

    double minEnergy = readings.getFirst().getEnergy();
    for(EnergyReading reading :  readings){
      if(minEnergy > reading.getEnergy()){
        minEnergy = reading.getEnergy();
      }
    }
      return minEnergy;

  }

  public List<EnergyReading> getAbnormalReadingsByDevice(int id){
    Device device = deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
    List<EnergyReading> readings = energyReadingRepository.findByDevice(device);
    List<EnergyReading> abnormals = new ArrayList<>();
    for(EnergyReading reading : readings){
      if(reading.getEnergy() > device.getMaxEnergy()){
        abnormals.add(reading);
      }
    }
    return abnormals;

  }

  public List<EnergyReading> getAbnormalReadingsByZone(int id){
    List<Device> devices= getDevicesByZone(id);
    List<EnergyReading> abdnormalReadings = new ArrayList<>();
    for(Device device : devices){
      for(EnergyReading reading : getAbnormalReadingsByDevice(device.getId())){
        abdnormalReadings.add(reading);
      }
    }
    return abdnormalReadings;
  }

  public List<Device> getDevicesByZone(int id){
    Zone zone = zoneRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Zone not found"));
    if(!zone.isActive()){
      throw new ResourceNotFoundException("Zone not found");
    }
    return deviceRepository.findByZoneAndActiveTrue(zone);
   
  }

  public List<Zone> getZonesByBuilding(int id){
    Building building = buildingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Building not found"));
    if(!building.isActive()){
      throw new ResourceNotFoundException("Building not found");
    }
    return zoneRepository.findByBuildingAndActiveTrue(building);
    
  }

  public List<Building> getBuildingsByOrganization(int id){
    Organization organization = organizationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Organization not found"));
    if(!organization.isActive()){
      throw new ResourceNotFoundException("Organization not found");
    }
    return buildingRepository.findByOrganizationAndActiveTrue(organization);
  }

  public double getTotalEnergyByOrganization(int id) {
    Organization organization = organizationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Organization not found"));
    if(!organization.isActive()){
      throw new ResourceNotFoundException("Organization not found");
    }
    List<Building> buildings = buildingRepository.findByOrganization(organization);
    
    double totalEnergy = 0.0;

    for (Building building : buildings) {
      totalEnergy += getTotalEnergyByBuilding(building.getId());
    }

    return totalEnergy;
  }

  public List<EnergyReading> getReadingsByDeviceAndPeriod(int id, LocalDateTime startDate, LocalDateTime finalDate){
    Device device = deviceRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Device not found"));
    List<EnergyReading> readings =  energyReadingRepository.findByDeviceAndTimestampBetween(device, startDate, finalDate);
    return readings;

  }

  public double getTotalEnergyByDeviceAndPeriod(int id, LocalDateTime startDate, LocalDateTime finalDate){
  List<EnergyReading> readings = getReadingsByDeviceAndPeriod(id, startDate, finalDate);
  double total= 0;
  for(EnergyReading reading : readings){
    total += reading.getEnergy();
  }
  return total;


  }

  public double getTotalEnergyByZoneAndPeriod(int id, LocalDateTime startDate, LocalDateTime finalDate){
    List<Device> devices = deviceRepository.findByZone(zoneRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Zone not found")));
    double total = 0.0;
    for(Device device : devices){
      total += getTotalEnergyByDeviceAndPeriod(device.getId(), startDate, finalDate);
    }
    return total;
  }

  public double getTotalEnergyByBuildingAndPeriod(int id, LocalDateTime startDate, LocalDateTime finalDate){
    List<Zone> zones = zoneRepository.findByBuilding(buildingRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Building not found")));
    double total = 0.0;
    for(Zone zone : zones) {
      total += getTotalEnergyByZoneAndPeriod(zone.getId(), startDate, finalDate);
      }
    return total;
   
  }

  public double getTotalEnergyByOrganizationAndPeriod(int id, LocalDateTime startDate, LocalDateTime finalDate){
    List<Building> buildings = buildingRepository.findByOrganization(organizationRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Organization not found")));
    double total = 0.0;
    for(Building building: buildings) {
      total += getTotalEnergyByBuildingAndPeriod(building.getId(), startDate, finalDate);
      }
    return total;
   
  }

  private void validateDateRange( LocalDateTime startDate, LocalDateTime finalDate) {
    if(startDate == null && finalDate == null){
        return;
    }
    if(startDate == null || finalDate == null){
        throw new InvalidRequestException("Both startDate and finalDate are required");
    }
    if(startDate.isAfter(finalDate)){
        throw new InvalidRequestException("startDate must be before finalDate");
    }
    
    }

    public double getTotalEnergyByDevice(int id, LocalDateTime startDate,LocalDateTime finalDate){
    validateDateRange(startDate, finalDate);
    if(startDate == null && finalDate == null){
        return getTotalEnergyByDevice(id);  
    } else{
      return getTotalEnergyByDeviceAndPeriod(id, startDate, finalDate);
    }
    
  }

  public double getTotalEnergyByZone(int id, LocalDateTime startDate,LocalDateTime finalDate){
    validateDateRange(startDate, finalDate);
    if(startDate == null && finalDate == null){
        return getTotalEnergyByZone(id);  
    } else{
      return getTotalEnergyByZoneAndPeriod(id, startDate, finalDate);
    }
    
  }

  public double getTotalEnergyByBuilding(int id, LocalDateTime startDate, LocalDateTime finalDate){
    validateDateRange(startDate, finalDate);
    if(startDate == null && finalDate == null){
        return getTotalEnergyByBuilding(id);  
    } else{
      return getTotalEnergyByBuildingAndPeriod(id, startDate, finalDate);
    }

  }

  public double getTotalEnergyByOrganization(int id, LocalDateTime startDate, LocalDateTime finalDate){
    validateDateRange(startDate, finalDate);
    if(startDate == null && finalDate == null){
        return getTotalEnergyByOrganization(id);  
    } else{
      return getTotalEnergyByOrganizationAndPeriod(id, startDate, finalDate);
    }

  }

  public List<EnergyReadingResponse> getReadingsByDevice(int id, LocalDateTime startDate, LocalDateTime finalDate){
    validateDateRange(startDate, finalDate);
    if(startDate == null && finalDate == null){
        return getReadingsByDevice(id);  
    } else{
      return energyReadingMapper.toResponseList(getReadingsByDeviceAndPeriod(id, startDate, finalDate));
      
    }

  }

  
}




