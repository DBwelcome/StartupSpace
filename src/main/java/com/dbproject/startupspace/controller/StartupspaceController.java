package com.dbproject.startupspace.controller;

import com.dbproject.startupspace.domain.dto.InitSpacesDTO;
import com.dbproject.startupspace.service.DataImportService;
import com.dbproject.startupspace.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
}
