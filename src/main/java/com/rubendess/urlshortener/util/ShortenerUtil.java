package com.rubendess.urlshortener.util;

import org.springframework.stereotype.Service;

/**
 * This code was referenced in the following site: https://hashids.org/
 */
@Service
public class ShortenerUtil {
    final static String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final static int ALPHABET_LENGTH = ALPHABET.length();

    public String encode(final Long id) {
        Long number = id;
        final StringBuilder strBuilder = new StringBuilder();

        while (number > 0) {
            final Long charIndex = number % ALPHABET_LENGTH;
            strBuilder.insert(0, ALPHABET.charAt(charIndex.intValue()));
            number = number / ALPHABET_LENGTH;
        }

        return strBuilder.toString();
    }

    public Long decode(final String hash) {
        Long num = 0L;

        for (int i = 0; i < hash.length(); i++) {
            int charIndex = ALPHABET.indexOf(hash.charAt(i));
            num = num * ALPHABET_LENGTH + charIndex;
        }

        return num;
    }
}
