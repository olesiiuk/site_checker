package com.example.site_reader.model.fileworker;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface FileParser {
    List<String> getURLsFromFile(MultipartFile file);
}
