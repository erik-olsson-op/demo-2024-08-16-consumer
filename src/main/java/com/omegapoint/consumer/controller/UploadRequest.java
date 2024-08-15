package com.omegapoint.consumer.controller;


@lombok.Data
@lombok.AllArgsConstructor
public class UploadRequest {
    private String name;
    private String base64;
}
