package com.dbproject.startupspace.domain.dto;

import com.sun.xml.txw2.annotation.XmlElement;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
public class SpaceListDTO {

    private List<SpaceDTO> areaCenterList = null;
}
