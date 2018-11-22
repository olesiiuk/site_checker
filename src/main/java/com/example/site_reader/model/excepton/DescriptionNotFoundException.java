package com.example.site_reader.model.excepton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DescriptionNotFoundException extends RuntimeException {

    public DescriptionNotFoundException(Integer id) {
        super("Could not find Site Description with id: " + id);
    }

}
