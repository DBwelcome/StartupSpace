package com.dbproject.startupspace.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.NumberFormat;
import java.text.ParseException;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "provspace")
public class ProvspaceEntity {

    @Id
    private Integer spaceId;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private ProvincialCenterEntity provincialCenterEntity;

    @Column(length = 50)
    private String spaceName;

    @Column(length = 50)
    private String spaceType;

    @Column
    private String seatType;

    @Column(length = 100)
    private String seatSize;

    @Column(length = 12)
    private String exclusiveArea;

    @Column(length = 12)
    private String publicArea;

    @Column(length = 100)
    private String rent;

    @Column(length = 100)
    private String deposit;

    @Column(columnDefinition = "TEXT")
    private String tenant;

    @Column
    private float score;

    @PreUpdate
    public void onPreUpdate() throws ParseException {
        if(Float.parseFloat(exclusiveArea) == 0.0) {
            score = 0;
            return;
        }
        if (rent.equals("0"))
            score = 10;
        else
            score = Float.parseFloat(exclusiveArea) / NumberFormat.getNumberInstance().parse(rent).longValue();
    }

    @PrePersist
    public void onPrePersist() throws ParseException {
        if(Float.parseFloat(exclusiveArea) == 0.0) {
            score = 0;
            return;
        }
        if (rent.equals("0"))
            score = 10;
        else
            score = Float.parseFloat(exclusiveArea) / NumberFormat.getNumberInstance().parse(rent).longValue();
    }

    @Builder
    public ProvspaceEntity(Integer spaceId, ProvincialCenterEntity provincialCenterEntity, String spaceName, String spaceType, String seatType, String seatSize,
                           String exclusiveArea, String publicArea, String rent, String deposit, String tenant, int score){
        this.spaceId = spaceId;
        this.provincialCenterEntity = provincialCenterEntity;
        this.spaceName = spaceName;
        this.spaceType = spaceType;
        this.seatType = seatType;
        this.seatSize = seatSize;
        this.exclusiveArea = exclusiveArea;
        this.publicArea = publicArea;
        this.rent = rent;
        this.deposit = deposit;
        this.tenant = tenant;
        this.score = score;
    }
}
