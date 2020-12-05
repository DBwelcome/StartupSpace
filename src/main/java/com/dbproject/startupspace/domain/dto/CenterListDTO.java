package com.dbproject.startupspace.domain.dto;

import com.dbproject.startupspace.domain.entity.MetropolitanCenterEntity;
import lombok.Data;

import java.util.List;

@Data
public class CenterListDTO {
    List<CenterDTO> areaCenterList;
}
