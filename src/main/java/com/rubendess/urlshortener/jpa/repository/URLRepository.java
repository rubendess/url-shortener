package com.rubendess.urlshortener.jpa.repository;

import com.rubendess.urlshortener.jpa.domain.URLDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends JpaRepository<URLDomain, Long> {

}
