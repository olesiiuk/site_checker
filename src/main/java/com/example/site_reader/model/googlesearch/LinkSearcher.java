package com.example.site_reader.model.googlesearch;

import java.util.List;
import java.util.Set;

public interface LinkSearcher {

    List<String> getSearchResultLinks(String searchRequest, int resultQuantity);

    List<String> getMultipleSearchResultLinks(String searchRequest, int resultQuantity);

}
