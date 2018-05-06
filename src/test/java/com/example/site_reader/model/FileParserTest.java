package com.example.site_reader.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class FileParserTest {
    private Set<String> sites;
    private MultipartFile file;


    @Before
    public void fillSites() {
        sites = new HashSet<>();

        sites.add("http://oskar-nails.h1n.ru/");
        sites.add("http://www.quizful.net/post/java-nio-tutorial");


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