package com.example.site_reader.model;

import com.example.site_reader.model.sitechecker.PrestaShopChecker;

public class PrestaShopCheckerExtended extends PrestaShopChecker {

    @Override
    protected String getDomain(String url) {
        return super.getDomain(url);
    }
}
