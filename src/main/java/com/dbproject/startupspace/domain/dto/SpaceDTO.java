package com.dbproject.startupspace.domain.dto;

import lombok.Builder;
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

    @Builder
    public SpaceDTO(int spceId, int cnterId, String spceNm, String spceTyNm, String seatTyNm, String seatSeNm, String dvrAr,
                    String cmnuseAr, String rentAmt, String grntyAmt, String seatCompany, int score){
        this.spceId = spceId;
        this.cnterId = cnterId;
        this.spceNm = spceNm;
        this.spceTyNm = spceTyNm;
        this.seatTyNm = seatTyNm;
        this.seatSeNm = seatSeNm;
        this.dvrAr = dvrAr;
        this.cmnuseAr = cmnuseAr;
        this.rentAmt = rentAmt;
        this.grntyAmt = grntyAmt;
        this.seatCompany = seatCompany;
        this.score = score;
    }
}
