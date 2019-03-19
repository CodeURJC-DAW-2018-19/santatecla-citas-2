package com.urjc.daw.practica.controller;

import com.urjc.daw.practica.model.Quote;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public interface QuoteController {

    String findOne();

    ResponseEntity<List<Quote>> findAll(@PathVariable int nPag);

    String postQuote(Model model, Quote quote,
                     @RequestParam("file") MultipartFile file);


    String editQuote(Model model, @PathVariable long id);

    public String deleteQuote(Model model, @PathVariable long id);

    String findByKeyword(@RequestParam(value = "keyword") String keyword, Model model);
}
