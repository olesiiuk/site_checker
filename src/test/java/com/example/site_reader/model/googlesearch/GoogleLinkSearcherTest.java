package com.example.site_reader.model.googlesearch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GoogleLinkSearcherTest {

    @Test
    public void getSearchResultLinksFromTestPage() throws IOException {
        GooglePageDownloader googlePageDownloader = mock(GooglePageDownloader.class);

        File file = new File("D:\\IdeaProjects\\Site_reader\\src\\test\\java\\com\\example\\site_reader\\model\\googlesearch\\test.html");
        Document doc = Jsoup.parse(file, null);

        when(googlePageDownloader.getPage("shop", 15))
                .thenReturn(doc);

        GooglePageParser googleLinkSearcher = new GooglePageParser(googlePageDownloader);

        Set<String> links = googleLinkSearcher.getSearchResultLinks("shop", 15);

        Set<String> expected = new HashSet<>();
        expected.add("http://link1");
        expected.add("http://link2");
        expected.add("http://link3");
        expected.add("http://link4");
        expected.add("http://link5");
        expected.add("http://link6");
        expected.add("http://link7");
        expected.add("http://link8");
        expected.add("http://link9");
        expected.add("http://link10");
        expected.add("http://link11");
        expected.add("http://link12");
        expected.add("http://link13");

        assertEquals(expected, links);
    }

    @Test
    public void getSearchResultLinksWithEmptyRequest() {
        GooglePageDownloader pageDownloader = new GooglePageDownloader();
        GooglePageParser pageParser = new GooglePageParser(pageDownloader);

        Set<String> links = pageParser.getSearchResultLinks("", 15);

        assertEquals(new HashSet<String>(), links);
    }

    @Test
    public void getSearchedResultLinksWithEmptyRequestQuantity() {
        GooglePageDownloader pageDownloader = new GooglePageDownloader();
        GooglePageParser pageParser = new GooglePageParser(pageDownloader);

        Set<String> links = pageParser.getSearchResultLinks("shop", 0);

        assertEquals(new HashSet<String>(), links);
    }

    @Test
    public void compareTwoRequestsAreSame() {
        GooglePageDownloader googlePageDownloader = new GooglePageDownloader();
        GooglePageParser pageParser = new GooglePageParser(googlePageDownloader);

        Set<String> firstSet = pageParser.getSearchResultLinks("shop", 5);
        firstSet.forEach(System.out::println);
        System.out.println("\n ===========================================\n");

        Set<String> secondSet = pageParser.getSearchResultLinks("shoes", 5);
        secondSet.forEach(System.out::println);

        System.out.println("First size: " + firstSet.size() + " Second size: " + secondSet.size());

        firstSet.forEach(System.out::println);
        System.out.println("\n ===========================================\n");
        secondSet.forEach(System.out::println);

        assertNotEquals(firstSet, secondSet);
    }

}