package com.dbproject.startupspace.domain.dto;

import com.dbproject.startupspace.domain.entity.MetropolitanCenterEntity;
import com.dbproject.startupspace.domain.entity.ProvincialCenterEntity;
import lombok.Builder;
import lombok.Data;

@Data
public class InitSpacesDTO {
    private String name;
    private String region;
    private String exclusiveArea;
    private String spaceType;
    private Integer vacantRoomCount;
    private Integer score;
    private String contactNum;
    private String homepageUrl;

    @Builder(builderMethodName = "of")
    private InitSpacesDTO(String name, String region, String exclusiveArea, String spaceType,
                          Integer vacantRoomCount, Integer score, String contactNum, String homepageUrl) {
        this.name = name;
        this.region = region;
        this.exclusiveArea = exclusiveArea;
        this.spaceType = spaceType;
        this.vacantRoomCount = vacantRoomCount;
        this.score = score;
        this.contactNum = contactNum;
        this.homepageUrl = homepageUrl;
    }

    public static InitSpacesDTO createMetroSpace(SpaceDTO spaceDTO, MetropolitanCenterEntity metropolitanCenterEntity) {
        return InitSpacesDTO.of()
                .name(metropolitanCenterEntity.getCenterNum())
                .region(metropolitanCenterEntity.getArea())
                .exclusiveArea(spaceDTO.getDvrAr())
                .spaceType(spaceDTO.getSpceTyNm())
                .vacantRoomCount(metropolitanCenterEntity.getSpaceCount())
                .score(spaceDTO.getScore())
                .contactNum(metropolitanCenterEntity.getTelephone())
                .homepageUrl(metropolitanCenterEntity.getHomepage())
                .build();
    }

    public static InitSpacesDTO createProvinceSpace(SpaceDTO spaceDTO, ProvincialCenterEntity provincialCenterEntity) {
        return InitSpacesDTO.of()
                .name(provincialCenterEntity.getCenterNum())
                .region(provincialCenterEntity.getArea())
                .exclusiveArea(spaceDTO.getDvrAr())
                .spaceType(spaceDTO.getSpceTyNm())
                .vacantRoomCount(provincialCenterEntity.getSpaceCount())
                .score(spaceDTO.getScore())
                .contactNum(provincialCenterEntity.getTelephone())
                .homepageUrl(provincialCenterEntity.getHomepage())
                .build();
    }

}
