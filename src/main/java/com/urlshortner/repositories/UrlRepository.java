package com.urlshortner.repositories;

import com.urlshortner.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {
    Optional<Url> findFirstByOrderByIdDesc();
    Optional<Url> findByShortUrl(String shortUrl);
    List<Url> findAll();
}
