package com.dbproject.startupspace.domain.dto;

import com.dbproject.startupspace.domain.entity.MetropolitanCenterEntity;
import com.dbproject.startupspace.domain.entity.ProvincialCenterEntity;
import lombok.Builder;
import lombok.Data;

@Data
public class InitSpacesDTO {

    private static final Integer FOR_ALL = 0;
    private static final String FOR_OLD = "중장년층";
    private static final String NON_FOR_OLD = "그외";

    private String name;
    private String region;
    private String exclusiveArea;
    private String spaceType;
    private Integer vacantRoomCount;
    private Float score;
    private String rentCost;
    private String contactNum;
    private String homepageUrl;
    private String age;

    @Builder(builderMethodName = "of")
    private InitSpacesDTO(String name, String region, String exclusiveArea, String spaceType, String age,
                          Integer vacantRoomCount, Float score, String rentCost, String contactNum, String homepageUrl) {
        this.name = name;
        this.region = region;
        this.exclusiveArea = exclusiveArea;
        this.spaceType = spaceType;
        this.age = age;
        this.vacantRoomCount = vacantRoomCount;
        this.score = score;
        this.rentCost = rentCost;
        this.contactNum = contactNum;
        this.homepageUrl = homepageUrl;
    }

    public static InitSpacesDTO createMetroSpace(SpaceDTO spaceDTO, MetropolitanCenterEntity metropolitanCenterEntity, Integer notEmptyCount) {
        return InitSpacesDTO.of()
                .name(metropolitanCenterEntity.getCenterNum())
                .region(metropolitanCenterEntity.getArea())
                .exclusiveArea(spaceDTO.getDvrAr())
                .spaceType(spaceDTO.getSpceTyNm())
                .age(getSpaceAge(metropolitanCenterEntity.getTarget()))
                .vacantRoomCount(metropolitanCenterEntity.getSpaceCount() - notEmptyCount)
                .score(spaceDTO.getScore())
                .rentCost(spaceDTO.getRentAmt())
                .contactNum(metropolitanCenterEntity.getTelephone())
                .homepageUrl(metropolitanCenterEntity.getHomepage())
                .build();
    }

    public static InitSpacesDTO createProvinceSpace(SpaceDTO spaceDTO, ProvincialCenterEntity provincialCenterEntity, Integer notEmptyCount) {
        return InitSpacesDTO.of()
                .name(provincialCenterEntity.getCenterNum())
                .region(provincialCenterEntity.getArea())
                .exclusiveArea(spaceDTO.getDvrAr())
                .spaceType(spaceDTO.getSpceTyNm())
                .age(getSpaceAge(provincialCenterEntity.getTarget()))
                .vacantRoomCount(provincialCenterEntity.getSpaceCount() - notEmptyCount)
                .score(spaceDTO.getScore())
                .rentCost(spaceDTO.getRentAmt())
                .contactNum(provincialCenterEntity.getTelephone())
                .homepageUrl(provincialCenterEntity.getHomepage())
                .build();
    }

    private static String getSpaceAge(Integer target) {
        if (FOR_ALL.equals(target)) {
            return NON_FOR_OLD;
        } else {
            return FOR_OLD;
        }
    }
}
