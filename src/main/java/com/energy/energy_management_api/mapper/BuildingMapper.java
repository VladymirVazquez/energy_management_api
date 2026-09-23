package com.energy.energy_management_api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.energy.energy_management_api.Building;
import com.energy.energy_management_api.dto.response.BuildingResponse;

@Component
public class BuildingMapper {

    public BuildingResponse toResponse(Building building) {
        BuildingResponse response = new BuildingResponse();

        response.setId(building.getId());
        response.setName(building.getName());
        response.setAddress(building.getAddress());
        response.setOrganizationName(building.getOrganization().getName());

        return response;
    }

    public List<BuildingResponse> toResponseList(List<Building> buildings) {
        List<BuildingResponse> responses = new ArrayList<>();

        for (Building building : buildings) {
            responses.add(toResponse(building));
        }

        return responses;
    }
}