package com.urjc.daw.practica.model;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Reference extends Item{

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "quote_id", nullable = false)
    private Quote quoteReferenced;

    @ManyToOne(cascade =  CascadeType.ALL,fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topicContaining;
}
