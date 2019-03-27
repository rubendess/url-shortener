package com.rubendess.urlshortener.controller;

import com.rubendess.urlshortener.dto.URLDto;
import com.rubendess.urlshortener.services.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/short-urls")
public class URLController {

    @Autowired
    URLService urlService;

    @PostMapping (produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> generateShortURL (@RequestBody URLDto urlDto) {

        return ResponseEntity.ok(urlService.generateShortURL(urlDto).getHashUrl());
    }

    @GetMapping(value = "/{hashUrl}")
    public void redirectToOriginalURLByShortURL (
            @NotNull @PathVariable("hashUrl") final String hashUrl,
            HttpServletResponse httpResponse) throws IOException {

        URLDto urlDto = urlService.getOriginalURLByCode(hashUrl);

        if (urlDto != null) {
            httpResponse.sendRedirect(urlDto.getUrl());
        } else {
            httpResponse.sendError(HttpStatus.NOT_FOUND.value());
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllURLs () {
        return ResponseEntity.ok(urlService.getAllURLs());
    }
}
