package com.example.site_reader.model.googlesearch;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class GooglePageParserTest {
    private ExtendedGoogleParser googleParser;

    @Before
    public void initialize() {
        GooglePageDownloader pageDownloader = mock(GooglePageDownloader.class);
        googleParser = new ExtendedGoogleParser(pageDownloader);
    }

    @Test
    public void cutSuffixLongLinkEndsWithSlashWithoutAMP() {
        String link = "https://www.google.com.ua/search?rlz=1C1CHBD_enUA750UA750&ei=rij8WveIH8a56ATXzrHwDA&q=thymeleaf+send+get+form&oq=thymeleaf+send+get+form&gs_l=psy-ab.3...584420.588030.0.589107.6.6.0.0.0.0.214.719.2j3j1.6.0....0...1c.1.64.psy-ab..0.3.396...0j0i22i30k1j35i39k1.0.-cfAld4hjzg";

        String result = googleParser.cutSuffix(link);

        String expected = "https://www.google.com.ua";

        assertEquals(expected, result);
    }

    @Test
    public void cutSuffixShortLink() {
        String link = "http://ex.ua";

        String result = googleParser.cutSuffix(link);

        String expected = "http://ex.ua";

        assertEquals(expected, result);
    }

    @Test
    public void cutSuffixLinkOfOneSymbol() {
        String link = "#";

        String result = googleParser.cutSuffix(link);

        String expected = "#";

        assertEquals(expected, result);
    }

    @Test
    public void cutSuffixLinkWithAmp() {
        String link = "https://www.google.com.ua/search?q=%D0%BF%D0%BE%D0%B1%D0%B8%D1%82%D0%BE%D0%B2%D1%8B%D0%B5+%D0%BE%D0%BF%D0%B5%D1%80%D0%B0%D1%86%D0%B8%D0%B8+java&rlz=1C1CHBD_enUA750UA750&oq=%D0%BF%D0%BE%D0%B1%D0%B8%D1%82%D0%BE%D0%B2%D1%8B%D0%B5+%D0%BE%D0%BF%D0%B5%D1%80%D0%B0%D1%86%D0%B8%D0%B8&aqs=chrome.1.69i57j0l5.6062j0j7&sourceid=chrome&ie=UTF-8";

        String result = googleParser.cutSuffix(link);

        String expected = "https://www.google.com.ua";

        assertEquals(expected, result);
    }

}