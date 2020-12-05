package com.dbproject.startupspace.domain.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
public class CenterDTO {
    private Integer cnterId;
    private String cnterNm;
    private Integer buldId;
    private String cnterTyNm;
    private String buldNm;
    private String zip;
    private String adr;
    private String telnm;
    private String fxnum;
    private String email;
    private String homepage;
    private String cnterIntrcn;
    private Integer spceCnt;
    private String area;
    private Boolean target;


}
