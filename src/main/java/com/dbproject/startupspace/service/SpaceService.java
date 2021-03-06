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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

@RequiredArgsConstructor
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
                    metrospaceEntity.getSpaceId(), metrospaceEntity.getMetropolitanCenterEntity().getCenterId(),
                    metrospaceEntity.getSpaceName(), metrospaceEntity.getSpaceType(),
                    metrospaceEntity.getSeatType(), metrospaceEntity.getSeatSize(),
                    metrospaceEntity.getExclusiveArea(), metrospaceEntity.getPublicArea(),
                    metrospaceEntity.getRent(), metrospaceEntity.getDeposit(),
                    metrospaceEntity.getTenant(), metrospaceEntity.getScore());
            MetropolitanCenterEntity metropolitanCenterEntity =
                    metropolitanCenterEntityRepository.findById(metrospaceEntity.getMetropolitanCenterEntity().getCenterId()).get();
            Integer emptySpaceCount = metrospaceEntityRepository.findNotEmptySpaceCount(metropolitanCenterEntity.getCenterId());

            initSpacesDTOs.add(InitSpacesDTO.createMetroSpace(spaceDto, metropolitanCenterEntity, emptySpaceCount));
            i++;
            if (i == 3) {
                break;
            }
        }
        i = 0;
        for (ProvspaceEntity provspaceEntity : provspaceEntities) {
            SpaceDTO spaceDto = SpaceDTO.create(
                    provspaceEntity.getSpaceId(), provspaceEntity.getProvincialCenterEntity().getCenterId(),
                    provspaceEntity.getSpaceName(), provspaceEntity.getSpaceType(),
                    provspaceEntity.getSeatType(), provspaceEntity.getSeatSize(),
                    provspaceEntity.getExclusiveArea(), provspaceEntity.getPublicArea(),
                    provspaceEntity.getRent(), provspaceEntity.getDeposit(),
                    provspaceEntity.getTenant(), provspaceEntity.getScore());
            ProvincialCenterEntity provincialCenterEntity =
                    provincialCenterEntityRepository.findById(provspaceEntity.getProvincialCenterEntity().getCenterId()).get();
            Integer emptySpaceCount = provspaceEntityRepository.findNotEmptySpaceCount(provincialCenterEntity.getCenterId());

            initSpacesDTOs.add(InitSpacesDTO.createProvinceSpace(spaceDto, provincialCenterEntity, emptySpaceCount));
            i++;
            if (i == 3) {
                break;
            }
        }

        initSpacesDTOs.sort(Comparator.comparing(InitSpacesDTO::getScore).reversed());

        return initSpacesDTOs;
    }

    int cnt = 0;
    public List<InitSpacesDTO> searchSpace(String region, String exclusiveArea, String Age,
                                           String rentCost, String spaceType, String vacantRoomCount) {
        List<MetrospaceEntity> metrospaceEntities = metrospaceEntityRepository.findAll();
        List<ProvspaceEntity> provspaceEntities = provspaceEntityRepository.findAll();
        List<InitSpacesDTO> initSpacesDTOs = new ArrayList<>();

        for (MetrospaceEntity metrospaceEntity : metrospaceEntities) {
            MetropolitanCenterEntity metropolitanCenterEntity =
                    metropolitanCenterEntityRepository.findById(metrospaceEntity.getMetropolitanCenterEntity().getCenterId()).get();
            Integer emptySpaceCount = metrospaceEntityRepository.findNotEmptySpaceCount(metropolitanCenterEntity.getCenterId());
            SpaceDTO spaceDto = SpaceDTO.create(
                    metrospaceEntity.getSpaceId(), metrospaceEntity.getMetropolitanCenterEntity().getCenterId(),
                    metrospaceEntity.getSpaceName(), metrospaceEntity.getSpaceType(),
                    metrospaceEntity.getSeatType(), metrospaceEntity.getSeatSize(),
                    metrospaceEntity.getExclusiveArea(), metrospaceEntity.getPublicArea(),
                    metrospaceEntity.getRent(), metrospaceEntity.getDeposit(),
                    metrospaceEntity.getTenant(), metrospaceEntity.getScore());

            String r = metropolitanCenterEntity.getArea();
            int e = (int)parseFloat(metrospaceEntity.getExclusiveArea());
            int ea = 9999;
            if(exclusiveArea != null){
                ea = (int) parseFloat(exclusiveArea);
            }
            int a = metropolitanCenterEntity.getTarget();
            String age = null;
            if(a == 1) {
                age = "1";
            }else if(a == 0){
                age = "0";
            }
            String re = metrospaceEntity.getRent().replace(",", "");
            int rc = parseInt(re);
            int rr = 999;
            if(rentCost != null){
                rr = parseInt(rentCost);
            }
            String st = metrospaceEntity.getSpaceType();
            int vrc = metropolitanCenterEntity.getSpaceCount();
            int vrc2 = 999;
            if(vacantRoomCount != null){
                vrc2 = parseInt(vacantRoomCount);
            }
            if((exclusiveArea == null || (e >= ea && e <= ea + 10) || ea >= 50) && (r.equals(region) || region == null) && (Age == null || age.equals(Age))
                    && (rentCost == null || (rc >= rr && rc <= rr + 10) || rc >= 50) && (st.equals(spaceType) || spaceType == null)
                    && (vacantRoomCount == null || (vrc >= vrc2 && vrc <= vrc2 + 10) || vrc >= 50)){
                initSpacesDTOs.add(InitSpacesDTO.createMetroSpace(spaceDto, metropolitanCenterEntity, emptySpaceCount));
                cnt++;
            }
            if(cnt == 10){
                break;
            }
        }

        cnt = 0;
        for (ProvspaceEntity provspaceEntity : provspaceEntities) {
            SpaceDTO spaceDto = SpaceDTO.create(
                    provspaceEntity.getSpaceId(), provspaceEntity.getProvincialCenterEntity().getCenterId(),
                    provspaceEntity.getSpaceName(), provspaceEntity.getSpaceType(),
                    provspaceEntity.getSeatType(), provspaceEntity.getSeatSize(),
                    provspaceEntity.getExclusiveArea(), provspaceEntity.getPublicArea(),
                    provspaceEntity.getRent(), provspaceEntity.getDeposit(),
                    provspaceEntity.getTenant(), provspaceEntity.getScore());
            ProvincialCenterEntity provincialCenterEntity =
                    provincialCenterEntityRepository.findById(provspaceEntity.getProvincialCenterEntity().getCenterId()).get();
            Integer emptySpaceCount = provspaceEntityRepository.findNotEmptySpaceCount(provincialCenterEntity.getCenterId());

            String r = provincialCenterEntity.getArea();
            int e = (int)parseFloat(provspaceEntity.getExclusiveArea());
            int ea = 9999;
            if(exclusiveArea != null){
                ea = (int) parseFloat(exclusiveArea);
            }
            int a = provincialCenterEntity.getTarget();
            String age = null;
            if(a == 1) {
                age = "1";
            }else if(a == 0){
                age = "0";
            }
            String re = provspaceEntity.getRent().replace(",", "");
            int rc = parseInt(re);
            int rr = 999;
            if(rentCost != null){
                rr = parseInt(rentCost);
            }
            String st = provspaceEntity.getSpaceType();
            int vrc = provincialCenterEntity.getSpaceCount();
            int vrc2 = 999;
            if(vacantRoomCount != null){
                vrc2 = parseInt(vacantRoomCount);
            }
            if((exclusiveArea == null || (e >= ea && e <= ea + 10) || e >= 50) && (r.equals(region)|| region == null) && (Age == null || age.equals(Age))
                    && (rentCost == null || (rc >= rr && rc <= rr + 10) || rc >= 50) && (st.equals(spaceType) || spaceType == null)
                    && (vacantRoomCount == null || (vrc >= vrc2 && vrc <= vrc2 + 10) || vrc >= 50)){
                initSpacesDTOs.add(InitSpacesDTO.createProvinceSpace(spaceDto, provincialCenterEntity, emptySpaceCount));
                cnt++;
            }
            if(cnt == 10){
                break;
            }
        }

        initSpacesDTOs.sort(Comparator.comparing(InitSpacesDTO::getScore).reversed());

        return initSpacesDTOs;
    }

}
