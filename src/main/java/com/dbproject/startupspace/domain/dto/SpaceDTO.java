package com.dbproject.startupspace.domain.dto;

import lombok.Data;

@Data
public class SpaceDTO {
    private Integer spceId;
    private Integer cnterId;
    private String spceNm;
    private String spceTyNm;
    private String seatTyNm;
    private String seatSeNm;
    private String dvrAr;
    private String cmnuseAr;
    private String rentAmt;
    private String grntyAmt;
    private String seatCompany;
    private Integer score;
}
