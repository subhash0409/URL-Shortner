package com.example.urlShortening.Url_Shortening.Controller;

import com.example.urlShortening.Url_Shortening.UrlDB.UrlDB;
import com.example.urlShortening.Url_Shortening.Repository.UrlShortenRepository;
import com.example.urlShortening.Url_Shortening.Service.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequestMapping("/urlShortening")
public class Controller {

    @Autowired
    UrlShorteningService shorteningService;
    @Autowired
    UrlShortenRepository shortenRepository;

    @PostMapping("/shorten")
    public String shortUrl(@RequestBody String longUrl){
      return shorteningService.shorten(longUrl);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirectToLongUrl(@PathVariable String shortUrl) {
        Optional<UrlDB> urlOptional = shortenRepository.findByShortUrl(shortUrl);

        if (urlOptional.isPresent()) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(urlOptional.get().getLongUrl());
            return redirectView;
        }
        else return null;
    }
}
