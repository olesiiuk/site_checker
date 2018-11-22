package com.example.site_reader.model;

import com.example.site_reader.model.fileworker.FileParserImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class FileParserTest {
    private List<String> sites;
    private MultipartFile file;


    @Before
    public void fillSites() {
        sites = new ArrayList<>();

        sites.add("http://www.quizful.net/post/java-nio-tutorial");
        sites.add("http://oskar-nails.h1n.ru/");

        String fileContent = "http://www.quizful.net/post/java-nio-tutorial\n" +
                "http://oskar-nails.h1n.ru/";
        InputStream in = new ByteArrayInputStream(fileContent.getBytes());

        try {
            file = new MockMultipartFile("file", in);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getURLsFromFile() {
        FileParserImpl parser = new FileParserImpl();

        assertEquals(sites, parser.getURLsFromFile(file));
    }
}