package com.example.site_reader.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrestaShopCheckerWithJSOUPTest {

    @Test
    public void testPrestaShopSite() {
        String siteUrl = "http://oskar-nails.h1n.ru/";

        PrestaShopCheckerWithJSOUP checker = new PrestaShopCheckerWithJSOUP();

        assertEquals("Presta shop site", checker.checkSite(siteUrl));
    }

    @Test
    public void testNotPrestaShopSite() {

        String siteUrl = "http://www.quizful.net/post/java-nio-tutorial";

        PrestaShopCheckerWithJSOUP checker = new PrestaShopCheckerWithJSOUP();

        assertEquals("Not a presta shop site", checker.checkSite(siteUrl));
    }
}
