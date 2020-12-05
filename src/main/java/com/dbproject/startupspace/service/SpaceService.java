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

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

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
                    metrospaceEntity.getSpaceId(), metrospaceEntity.getMetropolitanCenterEntity().getCenterId(),
                    metrospaceEntity.getSpaceName(), metrospaceEntity.getSpaceType(),
                    metrospaceEntity.getSeatType(), metrospaceEntity.getSeatSize(),
                    metrospaceEntity.getExclusiveArea(), metrospaceEntity.getPublicArea(),
                    metrospaceEntity.getRent(), metrospaceEntity.getDeposit(),
                    metrospaceEntity.getTenant(), metrospaceEntity.getScore());
            MetropolitanCenterEntity metropolitanCenterEntity =
                    metropolitanCenterEntityRepository.findById(metrospaceEntity.getMetropolitanCenterEntity().getCenterId()).get();
            initSpacesDTOs.add(InitSpacesDTO.createMetroSpace(spaceDto, metropolitanCenterEntity));
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
            initSpacesDTOs.add(InitSpacesDTO.createProvinceSpace(spaceDto, provincialCenterEntity));
            i++;
            if (i == 3) {
                break;
            }
        }
        return initSpacesDTOs;
    }



    public List<InitSpacesDTO> searchSpace(String region, String exclusiveArea, String Age,
                                           String rentCost, String spaceType, String vacantRoomCount) {
        List<MetrospaceEntity> metrospaceEntities = metrospaceEntityRepository.findAll();
        List<ProvspaceEntity> provspaceEntities = provspaceEntityRepository.findAll();
        List<InitSpacesDTO> initSpacesDTOs = new ArrayList<>();

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

            String r = metropolitanCenterEntity.getArea();
            int e = (int)parseFloat(metrospaceEntity.getExclusiveArea());
            int ea = 9999;
            if(exclusiveArea != null){
                ea = (int) parseFloat(exclusiveArea);
            }
            int a = metropolitanCenterEntity.getTarget();
            String age = null;
            if(a == 1) {
                age = "중장년";
            }else if(a == 0){
                age = "청년";
            }
            String re = metrospaceEntity.getRent().replace(",", "");
            int rc = parseInt(re);
            int rr = 99999999;
            if(rentCost != null){
                rr = parseInt(rentCost);
            }
            String st = metrospaceEntity.getSpaceType();
            int vrc = metropolitanCenterEntity.getSpaceCount();
            int vrc2 = 999;
            if(vacantRoomCount != null){
                vrc2 = parseInt(vacantRoomCount);
            }
            if((exclusiveArea == null || (e >= ea && e <= ea + 10)) && (r.equals(region) || region == null) && (Age == null || age.equals(Age))
                    && (rentCost == null || (rc >= rr && rc <= rr + 100000)) && (st.equals(spaceType) || spaceType == null)
                    && (vacantRoomCount == null || (vrc >= vrc2 && vrc <= vrc2 + 10))){
                initSpacesDTOs.add(InitSpacesDTO.createMetroSpace(spaceDto, metropolitanCenterEntity));
            }
        }

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

            String r = provincialCenterEntity.getArea();
            int e = (int)parseFloat(provspaceEntity.getExclusiveArea());
            int ea = 9999;
            if(exclusiveArea != null){
                ea = (int) parseFloat(exclusiveArea);
            }
            int a = provincialCenterEntity.getTarget();
            String age = null;
            if(a == 1) {
                age = "중장년";
            }else if(a == 0){
                age = "청년";
            }
            String re = provspaceEntity.getRent().replace(",", "");
            int rc = parseInt(re);
            int rr = 99999999;
            if(rentCost != null){
                rr = parseInt(rentCost);
            }
            String st = provspaceEntity.getSpaceType();
            int vrc = provincialCenterEntity.getSpaceCount();
            int vrc2 = 999;
            if(vacantRoomCount != null){
                vrc2 = parseInt(vacantRoomCount);
            }
            if((exclusiveArea == null || (e >= ea && e <= ea + 10)) && (r.equals(region)|| region == null) && (Age == null || age.equals(Age))
                    && (rentCost == null || (rc >= rr && rc <= rr + 100000)) && (st.equals(spaceType) || spaceType == null)
                    && (vacantRoomCount == null || (vrc >= vrc2 && vrc <= vrc2 + 10))){
                initSpacesDTOs.add(InitSpacesDTO.createProvinceSpace(spaceDto, provincialCenterEntity));
            }
        }

        return initSpacesDTOs;
    }

}
