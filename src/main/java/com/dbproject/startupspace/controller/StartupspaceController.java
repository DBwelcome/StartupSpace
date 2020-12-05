package com.dbproject.startupspace.controller;

import com.dbproject.startupspace.domain.dto.InitSpacesDTO;
import com.dbproject.startupspace.service.DataImportService;
import com.dbproject.startupspace.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class StartupspaceController {

    private final DataImportService dataImportService;
    private final SpaceService spaceService;

    @GetMapping("/getInitialData")
    public void get() throws UnsupportedEncodingException {
        dataImportService.getInitialData();
    }

    @GetMapping("/")
    public List<InitSpacesDTO> list() {
        return spaceService.getList();
    }

    @GetMapping("/filter")
    public List<InitSpacesDTO> search(@RequestParam(value="region", required = false) String region,
                                      @RequestParam(value="exclusiveArea", required = false) String exclusiveArea,
                                      @RequestParam(value="age", required = false) String age,
                                      @RequestParam(value="rentCost", required = false) String rentCost,
                                      @RequestParam(value="spaceType", required = false) String spaceType,
                                      @RequestParam(value="vacantRoomCount", required = false) String vacantRoomCount){
        System.out.println(region);
        return spaceService.searchSpace(region, exclusiveArea, age, rentCost, spaceType, vacantRoomCount);
    }
}

