package com.example.site_reader.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface FileParser {
    Set<String> getURLsFromFile(MultipartFile file);
}
