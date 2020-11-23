package com.dbproject.startupspace.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "space")
public class SpaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spaceId;

    @Column(length=50, nullable = false)
    private String centerId;

    @Column(length = 50)
    private String spaceName;

    @Column(length = 50)
    private String spaceType;

    @Column
    private int seatType;

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
    private int score;

    @Builder
    public SpaceEntity(Long spaceId, String centerId, String spaceName, String spaceType, int seatType, String seatSize,
                       String exclusiveArea, String publicArea, String rent, String deposit, String tenant, int score){
        this.spaceId = spaceId;
        this.centerId = centerId;
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
