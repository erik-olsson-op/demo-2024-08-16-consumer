package com.omegapoint.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class ConsumerController {

    private final Map<String, MultipartFile> files = new HashMap<>();

    @RequestMapping(
            consumes = "text/csv",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            path = "api/v1/upload"
    )
    public ResponseEntity<String> postCsv(@RequestParam("file") MultipartFile file) {
        log.info(file.getOriginalFilename());
        return ResponseEntity.ok().body(file.getOriginalFilename());
    }

    @GetMapping(path = "api/v1")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok().body("PONG");
    }

}
