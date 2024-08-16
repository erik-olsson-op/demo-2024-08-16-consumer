package com.omegapoint.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
public class ConsumerController {

    private final Map<String, byte[]> files = new HashMap<>();

    @RequestMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            path = "api/v1/upload"
    )
    public ResponseEntity<String> postCsv(@RequestBody UploadRequest uploadRequest) {
        log.info(uploadRequest.getName());
        var bytes = Base64.getDecoder()
                .decode(uploadRequest.getBase64());
        files.put(uploadRequest.getName(), bytes);
        return ResponseEntity.ok(uploadRequest.getName());
    }

    @RequestMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET,
            path = "api/v1/{fileName}"
    )
    public ResponseEntity<String> csv(@PathVariable String fileName) {
        var bytes = files.get(fileName);
        if (Objects.isNull(bytes)) {
            return ResponseEntity.ok("No CSV file found");
        }
        return ResponseEntity.ok(new String(bytes));
    }
}
