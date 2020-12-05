package com.dbproject.startupspace.controller;

import com.dbproject.startupspace.domain.dto.SpaceDTO;
import com.dbproject.startupspace.service.DataImportService;
import com.dbproject.startupspace.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class StartupspaceController {

    @Autowired
    private DataImportService dataImportService;

    @Autowired
    private SpaceService spaceService;

    @GetMapping("/getInitialData")
    public void get() throws UnsupportedEncodingException {
        dataImportService.getInitialData();
    }

    @GetMapping("/")
    public List<SpaceDTO> list(){
        List<SpaceDTO> spaceList = spaceService.getList();
        return spaceList;
    }
}
