package com.example.site_reader.model.googlesearch;

public class ExtendedGoogleParser extends GooglePageParser {

    public ExtendedGoogleParser(GooglePageDownloader googlePageDownloader) {
        super(googlePageDownloader);
    }

    @Override
    protected String cutSuffix(String link) {
        return super.cutSuffix(link);
    }
}
