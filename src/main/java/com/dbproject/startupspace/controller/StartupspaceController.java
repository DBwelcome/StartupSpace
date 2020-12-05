package com.dbproject.startupspace.controller;

import com.dbproject.startupspace.service.InitialDataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class StartupspaceController {

    @Autowired
    private InitialDataImportService initialDataImportService;

    @GetMapping("/getInitialData")
    public void get() throws UnsupportedEncodingException {
        initialDataImportService.getInitialData();
    }
}
