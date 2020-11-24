package com.dbproject.startupspace.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="metropolitancenter")
public class MetropolitanCenterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int centerId;

    @Column(length = 50, nullable = false)
    private String centerNum;

    @Column(length = 50, nullable = false)
    private String centerType;

    @Column(length = 50, nullable = false)
    private String buildingName;

    @Column(nullable = false)
    private int postNum;

    @Column(length = 100,nullable = false)
    private String address;

    @Column(length = 12)
    private String telephone;

    @Column(length = 12)
    private String fax;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String homepage;

    @Column(columnDefinition = "TEXT")
    private String centerIntro;

    @Column(nullable = false)
    private int spaceCount;

    @Column(length = 2)
    private String area;

    @Column(nullable = false)
    private int target;

    @Builder
    public MetropolitanCenterEntity(int centerId, String centerNum, String centerType, String buildingName, int postNum,
                                    String address, String telephone, String fax, String email, String homepage,
                                    String centerIntro, int spaceCount, String area, int target){
        this.centerId = centerId;
        this.centerNum = centerNum;
        this.centerType = centerType;
        this.buildingName = buildingName;
        this.postNum = postNum;
        this.address = address;
        this.telephone = telephone;
        this.fax = fax;
        this.email = email;
        this.homepage = homepage;
        this.centerIntro = centerIntro;
        this.spaceCount = spaceCount;
        this.area = area;
        this.target = target;
    }
}
