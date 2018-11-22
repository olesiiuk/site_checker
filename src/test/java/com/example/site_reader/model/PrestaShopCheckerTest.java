package com.example.site_reader.model;

import com.example.site_reader.model.sitechecker.PrestaShopChecker;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrestaShopCheckerTest {

    //TODO replace this test with working one
//    @Test
//    public void testPrestaShopSite() {
//        String siteUrl = "http://oskar-nails.h1n.ru/";
//
//        PrestaShopChecker checker = new PrestaShopChecker();
//
//        assertTrue(checker.isPrestaShopSite(siteUrl));
//    }

    @Test
    public void testNotPrestaShopSite() {

        String siteUrl = "http://www.quizful.net/post/java-nio-tutorial";

        PrestaShopChecker checker = new PrestaShopChecker();

        assertFalse(checker.isPrestaShopSite(siteUrl));
    }

    @Test
    public void makeDomainTest() {
        String url = "http://oskar-nails.h1n.ru/";

        PrestaShopCheckerExtended prestaShopChecker = new PrestaShopCheckerExtended();

        String expected = "oskar-nails.h1n.ru/";

        String actual = prestaShopChecker.getDomain(url);
    }
}
