package com.dbproject.startupspace.controller;

import com.dbproject.startupspace.domain.dto.SpaceDTO;
import com.dbproject.startupspace.service.InitialDataImportService;
import com.dbproject.startupspace.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class StartupspaceController {

    private InitialDataImportService initialDataImportService;
    private SpaceService spaceService;

    @GetMapping("/getInitialData")
    public void get() throws UnsupportedEncodingException {
        initialDataImportService.getInitialData();
    }

    @GetMapping("/")
    public List<SpaceDTO> list(){
        List<SpaceDTO> spaceList = spaceService.getList();
        return spaceList;
    }
}
