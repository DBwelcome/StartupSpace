package com.dbproject.startupspace.domain.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "provincialcenter")
public class ProvincialCenterEntity {

    @Id
    private Integer centerId;

    @ManyToOne
    @JoinColumn(name="provspace_no")
    private ProvspaceEntity provspaceEntity;

    @Column(length = 50)
    private String centerNum;

    @Column(length = 50)
    private String centerType;

    @Column(length = 50)
    private String buildingName;

    @Column
    private Integer postNum;

    @Column(length = 100)
    private String address;

    @Column(length = 40)
    private String telephone;

    @Column(length = 40)
    private String fax;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String homepage;

    @Column(columnDefinition = "TEXT")
    private String centerIntro;

    @Column
    private Integer spaceCount;

    @Column(length = 4)
    private String area;

    @Column
    private Integer target;

    @Builder
    public ProvincialCenterEntity(int centerId, String centerNum, String centerType, String buildingName, Integer postNum,
                                    String address, String telephone, String fax, String email, String homepage,
                                    String centerIntro, int spaceCount){
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

    }
}
