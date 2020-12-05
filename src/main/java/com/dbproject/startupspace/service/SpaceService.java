package com.dbproject.startupspace.service;


import com.dbproject.startupspace.domain.dto.InitSpacesDTO;
import com.dbproject.startupspace.domain.dto.SpaceDTO;
import com.dbproject.startupspace.domain.entity.MetropolitanCenterEntity;
import com.dbproject.startupspace.domain.entity.MetrospaceEntity;
import com.dbproject.startupspace.domain.entity.ProvincialCenterEntity;
import com.dbproject.startupspace.domain.entity.ProvspaceEntity;
import com.dbproject.startupspace.repository.MetropolitanCenterEntityRepository;
import com.dbproject.startupspace.repository.MetrospaceEntityRepository;
import com.dbproject.startupspace.repository.ProvincialCenterEntityRepository;
import com.dbproject.startupspace.repository.ProvspaceEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class SpaceService {

    private final MetropolitanCenterEntityRepository metropolitanCenterEntityRepository;
    private final MetrospaceEntityRepository metrospaceEntityRepository;
    private final ProvincialCenterEntityRepository provincialCenterEntityRepository;
    private final ProvspaceEntityRepository provspaceEntityRepository;

    public List<InitSpacesDTO> getList() {
        List<MetrospaceEntity> metrospaceEntities = metrospaceEntityRepository.findAll();
        List<ProvspaceEntity> provspaceEntities = provspaceEntityRepository.findAll();
        List<InitSpacesDTO> initSpacesDTOs = new ArrayList<>();
        int i = 0;
        for (MetrospaceEntity metrospaceEntity : metrospaceEntities) {
            SpaceDTO spaceDto = SpaceDTO.create(
                    metrospaceEntity.getSpaceId(), metrospaceEntity.getCenterId(),
                    metrospaceEntity.getSpaceName(), metrospaceEntity.getSpaceType(),
                    metrospaceEntity.getSeatType(), metrospaceEntity.getSeatSize(),
                    metrospaceEntity.getExclusiveArea(), metrospaceEntity.getPublicArea(),
                    metrospaceEntity.getRent(), metrospaceEntity.getDeposit(),
                    metrospaceEntity.getTenant(), metrospaceEntity.getScore());
            MetropolitanCenterEntity metropolitanCenterEntity =
                    metropolitanCenterEntityRepository.findById(metrospaceEntity.getCenterId()).get();
            initSpacesDTOs.add(InitSpacesDTO.createMetroSpace(spaceDto, metropolitanCenterEntity));
            i++;
            if (i == 3) {
                break;
            }
        }
        i = 0;
        for (ProvspaceEntity provspaceEntity : provspaceEntities) {
            SpaceDTO spaceDto = SpaceDTO.create(
                    provspaceEntity.getSpaceId(), provspaceEntity.getCenterId(),
                    provspaceEntity.getSpaceName(), provspaceEntity.getSpaceType(),
                    provspaceEntity.getSeatType(), provspaceEntity.getSeatSize(),
                    provspaceEntity.getExclusiveArea(), provspaceEntity.getPublicArea(),
                    provspaceEntity.getRent(), provspaceEntity.getDeposit(),
                    provspaceEntity.getTenant(), provspaceEntity.getScore());
            ProvincialCenterEntity provincialCenterEntity =
                    provincialCenterEntityRepository.findById(provspaceEntity.getCenterId()).get();
            initSpacesDTOs.add(InitSpacesDTO.createProvinceSpace(spaceDto, provincialCenterEntity));
            i++;
            if (i == 3) {
                break;
            }
        }
        return initSpacesDTOs;
    }
}
