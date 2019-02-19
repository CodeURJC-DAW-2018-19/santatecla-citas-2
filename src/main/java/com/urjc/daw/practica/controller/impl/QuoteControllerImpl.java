package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.QuoteController;
import com.urjc.daw.practica.model.Quote;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class QuoteControllerImpl implements QuoteController {
    @Override
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
    }
}
