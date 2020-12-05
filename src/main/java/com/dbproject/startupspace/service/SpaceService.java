package com.dbproject.startupspace.service;


import com.dbproject.startupspace.domain.dto.SpaceDTO;
import com.dbproject.startupspace.domain.entity.MetrospaceEntity;
import com.dbproject.startupspace.domain.entity.ProvspaceEntity;
import com.dbproject.startupspace.repository.MetrospaceEntityRepository;
import com.dbproject.startupspace.repository.ProvspaceEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class SpaceService {
    private MetrospaceEntityRepository metrospaceEntityRepository;
    private ProvspaceEntityRepository provspaceEntityRepository;

    @Transactional
    public List<SpaceDTO> getList() {
        List<MetrospaceEntity> metrospaceEntities = metrospaceEntityRepository.findAll();
        List<ProvspaceEntity> provspaceEntities = provspaceEntityRepository.findAll();
        List<SpaceDTO> spaceDtoList = new ArrayList<>();
        int i = 0;
        for(MetrospaceEntity metrospaceEntity : metrospaceEntities){
            SpaceDTO spaceDto = SpaceDTO.builder()
                    .spceId(metrospaceEntity.getSpaceId())
                    .cnterId(metrospaceEntity.getCenterId())
                    .spceNm(metrospaceEntity.getSpaceName())
                    .spceTyNm(metrospaceEntity.getSpaceType())
                    .seatTyNm(metrospaceEntity.getSeatType())
                    .seatSeNm(metrospaceEntity.getSeatSize())
                    .dvrAr(metrospaceEntity.getExclusiveArea())
                    .cmnuseAr(metrospaceEntity.getPublicArea())
                    .rentAmt(metrospaceEntity.getRent())
                    .grntyAmt(metrospaceEntity.getDeposit())
                    .seatCompany(metrospaceEntity.getTenant())
                    .score(metrospaceEntity.getScore())
                    .build();
            spaceDtoList.add(spaceDto);
            i++;
            if(i == 3){
                break;
            }
        }
        i = 0;
        for(ProvspaceEntity provspaceEntity : provspaceEntities){
            SpaceDTO spaceDto = SpaceDTO.builder()
                    .spceId(provspaceEntity.getSpaceId())
                    .cnterId(provspaceEntity.getCenterId())
                    .spceNm(provspaceEntity.getSpaceName())
                    .spceTyNm(provspaceEntity.getSpaceType())
                    .seatTyNm(provspaceEntity.getSeatType())
                    .seatSeNm(provspaceEntity.getSeatSize())
                    .dvrAr(provspaceEntity.getExclusiveArea())
                    .cmnuseAr(provspaceEntity.getPublicArea())
                    .rentAmt(provspaceEntity.getRent())
                    .grntyAmt(provspaceEntity.getDeposit())
                    .seatCompany(provspaceEntity.getTenant())
                    .score(provspaceEntity.getScore())
                    .build();
            spaceDtoList.add(spaceDto);
            i++;
            if(i == 3){
                break;
            }
        }
        return spaceDtoList;
    }
}
