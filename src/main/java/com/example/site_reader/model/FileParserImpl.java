package com.example.site_reader.model;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
public class FileParserImpl implements FileParser {

    @Override
    public Set<String> getURLsFromFile(MultipartFile file) {
        Set<String> urls = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                urls.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return urls;
    }
}
