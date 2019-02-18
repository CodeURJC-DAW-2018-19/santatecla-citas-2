package com.urjc.daw.practica.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public abstract class Item {

    @Id
    @GeneratedValue
    private Long id;
}
