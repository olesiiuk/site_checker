package com.example.site_reader.model.fileworker;

import com.example.site_reader.model.fileworker.FileParser;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class FileParserImpl implements FileParser {

    @Override
    public List<String> getURLsFromFile(MultipartFile file) {
        List<String> urls = new ArrayList<>();

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
