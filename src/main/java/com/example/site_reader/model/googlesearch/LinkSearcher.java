package com.example.site_reader.model.googlesearch;

import java.util.Set;

public interface LinkSearcher {

    Set<String> getSearchResultLinks(String searchRequest, int resultQuantity);

}
