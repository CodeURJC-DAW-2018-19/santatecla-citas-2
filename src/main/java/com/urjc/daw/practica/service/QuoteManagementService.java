package com.urjc.daw.practica.service;

import com.urjc.daw.practica.model.Quote;

import com.urjc.daw.practica.model.QuoteImage;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


public interface QuoteManagementService {
    Optional<Quote> findOne(Long id);
    //Page<Quote> findAll(int nPage, int nQuotes);

    Page<Quote> findAll(int nPag, int quotesPerPage);

    Quote save(Quote quote);

    Quote editQuote(Quote quote);

    Quote deleteQuote(Long id);

    List<Quote> findByIdDiferrentThan(List<Long> ids);

    List<Quote> findByKeyword(String keyword);

    List<Quote> findByAuthor(String author);

    List<Quote> findByBook(String book);

    QuoteImage saveQuoteImage(MultipartFile file, Quote quote);
}
