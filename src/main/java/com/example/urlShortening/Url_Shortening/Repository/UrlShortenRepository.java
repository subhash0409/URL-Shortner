package com.example.urlShortening.Url_Shortening.Repository;

import com.example.urlShortening.Url_Shortening.UrlDB.UrlDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UrlShortenRepository extends JpaRepository<UrlDB,Integer> {

    @Query(value = "SELECT * FROM urldb WHERE short_url = :shortUrl", nativeQuery = true)
    Optional<UrlDB> findByShortUrl(String shortUrl);
}
