package com.urjc.daw.practica.controller.impl;

import com.urjc.daw.practica.controller.QuoteController;
import com.urjc.daw.practica.model.Quote;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class QuoteControllerImpl implements QuoteController {
    @Override

    @RequestMapping(value = "/quote/@{id}",method = RequestMethod.GET)
    public String getQuote() {
        //ToDo Pedir a quote service que devuelva el quote segun el id
        return "quoteForm";
    }

    @Override
    @RequestMapping(value = "/quote",method = RequestMethod.GET)
    public String findAll() {
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
    }
}
