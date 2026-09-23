package com.energy.energy_management_api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.energy.energy_management_api.Zone;
import com.energy.energy_management_api.dto.response.ZoneResponse;

@Component
public class ZoneMapper {

    public ZoneResponse toResponse(Zone zone) {
        ZoneResponse response = new ZoneResponse();

        response.setId(zone.getId());
        response.setName(zone.getName());
        response.setBuildingName(zone.getBuilding().getName());

        return response;
    }

    public List<ZoneResponse> toResponseList(List<Zone> zones) {
        List<ZoneResponse> responses = new ArrayList<>();

        for (Zone zone : zones) {
            responses.add(toResponse(zone));
        }

        return responses;
    }
}