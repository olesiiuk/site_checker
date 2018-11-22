package com.example.site_reader.model.excepton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SiteNotFoundException extends RuntimeException {

    public SiteNotFoundException(String domain) {
        super("Couldn't find site with domain: " + domain + " in databse");
    }

}
