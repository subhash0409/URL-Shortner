package com.example.urlShortening.Url_Shortening.Service;

import com.example.urlShortening.Url_Shortening.UrlDB.UrlDB;
import com.example.urlShortening.Url_Shortening.Repository.UrlShortenRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UrlShorteningService {

    @Autowired
    UrlShortenRepository shortenRepository;

    public String shorten(String longUrl) {
        if (longUrl == null)
            throw new IllegalArgumentException();
        if (isValidURL(longUrl)) {
            UrlDB entry = new UrlDB();
            entry.setLongUrl(longUrl);
            entry.setShortUrl(RandomStringUtils.randomAlphabetic(5));
            shortenRepository.save(entry);
            return "http://localhost:9090/urlShortening/" + entry.getShortUrl();
        } else return "enter a VALID URL";
    }

    private static boolean isValidURL(String url) {
        String regex = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]"
                + "{2,256}\\.[a-z]"
                + "{2,6}\\b([-a-zA-Z0-9@:%"
                + "._\\+~#?&//=]*)";

        Pattern p = Pattern.compile(regex);

        if (url == null) {
            return false;
        }

        Matcher m = p.matcher(url);

        return m.matches();
    }

}
