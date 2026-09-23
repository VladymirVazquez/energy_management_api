package com.energy.energy_management_api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.energy.energy_management_api.Device;
import com.energy.energy_management_api.dto.response.DeviceResponse;

@Component
public class DeviceMapper {

    public DeviceResponse toResponse(Device device) {
        DeviceResponse response = new DeviceResponse();

        response.setId(device.getId());
        response.setName(device.getName());
        response.setType(device.getType());
        response.setMaxEnergy(device.getMaxEnergy());
        response.setZoneName(device.getZone().getName());

        return response;
    }

    public List<DeviceResponse> toResponseList(List<Device> devices) {
        List<DeviceResponse> responses = new ArrayList<>();

        for (Device device : devices) {
            responses.add(toResponse(device));
        }

        return responses;
    }
}