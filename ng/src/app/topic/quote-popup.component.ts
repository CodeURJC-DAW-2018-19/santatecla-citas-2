import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {Quote, QuoteService} from '../quote/quote.service';
import {LoginService} from '../user/login.service';

@Component({
    templateUrl:'../quote/quote-list.component.html'
})

export class QuotePopupComponent implements OnInit{

    quotes: Quote[];
    
    constructor(private router: Router, private service:QuoteService,
        public loginService: LoginService){}
        
    ngOnInit(){
        this.service.findAllUnpaged().subscribe(
            quotes => this.quotes=quotes,
            error => console.log(error)
        );
    }

    /**
     * Botón para añadir una cita, redirección a quote/new 
     * envía a quote-form
     */
    

}