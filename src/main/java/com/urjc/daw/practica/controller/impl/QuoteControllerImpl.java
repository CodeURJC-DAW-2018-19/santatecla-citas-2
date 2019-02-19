package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.QuoteController;
import com.urjc.daw.practica.model.Quote;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class QuoteControllerImpl implements QuoteController {
    @Override
<<<<<<< HEAD
    @RequestMapping(value = "/quotes/@{id}",method = RequestMethod.GET)
    public Quote getQuote() {
        return null;
    }

    @Override
    @RequestMapping(value = "/quotes",method = RequestMethod.GET)
    public List<Quote> getAllQuotes() {
        return null;
    }

    @Override
    @RequestMapping(value = "/quotes/@{id}",method = RequestMethod.POST)
    public boolean postQuote(Quote quote) {
        return false;
    }

    @Override
    @RequestMapping(value = "/quotes/@{id}",method = RequestMethod.PUT)
    public boolean editQuote(Quote quote) {
        return false;
    }

    @Override
    @RequestMapping(value = "/quotes/@{id}",method = RequestMethod.DELETE)
    public boolean deleteQuote(Quote quote) {
        return false;
=======
    @RequestMapping(value = "/quote/@{id}",method = RequestMethod.GET)
    public String getQuote() {
        //ToDo Pedir a quote service que devuelva el quote segun el id
        return "quoteForm";
    }

    @Override
    @RequestMapping(value = "/quote",method = RequestMethod.GET)
    public String getAllQuotes() {
        //ToDo Pedir al servicio de quote que devuelva todos los quotes
        return "quote";
    }

    @Override
    @RequestMapping(value = "/quote/@{id}",method = RequestMethod.POST)
    public String postQuote(Quote quote) {
        //ToDo Pedir al servicio de quotes que añada una quote nueva al repositorio
        return "quote";
    }

    @Override
    @RequestMapping(value = "/quote/@{id}",method = RequestMethod.PUT)
    public String editQuote(Quote quote) {
        //ToDo Pedir al sevicio de quotes que modifique una quote existente según el id
        return "quote";
    }

    @Override
    @RequestMapping(value = "/quote/@{id}",method = RequestMethod.DELETE)
    public String deleteQuote(Quote quote) {
        //ToDo Pedir al servicio de quotes que borre una quote segun el id
        return "quote";
>>>>>>> f69c1d1113782ef55188bf99fa163a5d6aa24ee8
    }
}
