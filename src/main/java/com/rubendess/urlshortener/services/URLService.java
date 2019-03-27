package com.rubendess.urlshortener.services;

import com.mysql.cj.util.StringUtils;
import com.rubendess.urlshortener.dto.URLDto;
import com.rubendess.urlshortener.jpa.domain.URLDomain;
import com.rubendess.urlshortener.jpa.repository.*;
import com.rubendess.urlshortener.util.ShortenerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.MalformedParametersException;

@Service
public class URLService {

    @Autowired
    URLRepository urlRepository;

    @Autowired
    ShortenerUtil shortenerUtil;

    @Value("${url-shortener.host-redirect}")
    String hostRedirect;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public URLDto generateShortURL(URLDto urlDto) {

        final URLDomain urlDomain = new URLDomain();
        urlDomain.setOriginalURL(urlDto.getUrl());

        // save URL to get the generated ID
        urlRepository.save(urlDomain);

        // generate hash from ID
        final String urlHash = shortenerUtil.encode(urlDomain.getId());
        urlDomain.setHashURL(urlHash);

        // save URL with the hash
        urlRepository.save(urlDomain);

        final String url = hostRedirect.concat("/").concat(urlDomain.getHashURL());
        final URLDto urlDtoReturn = new URLDto();
        urlDtoReturn.setId(urlDomain.getId());
        urlDtoReturn.setUrl(urlDto.getUrl());
        urlDtoReturn.setHashUrl(url);

        return urlDtoReturn;
    }

    public URLDto getOriginalURLByCode (String hash) {

        if (StringUtils.isNullOrEmpty(hash))
            throw new MalformedParametersException("Hash parameter is required and cannot be null or empty.");

        // decode hash to get the ID
        final Long id = shortenerUtil.decode(hash);

        // get URL in the DB
        final URLDomain urlDomain = urlRepository.getOne(id);

        if (urlDomain != null) {
            final URLDto urlDto = new URLDto();
            urlDto.setUrl(urlDomain.getOriginalURL());
            urlDto.setHashUrl(urlDomain.getHashURL());

            return urlDto;
        }

        return null;
    }

    public java.util.List getAllURLs () {
        return urlRepository.findAll();
    }
}
