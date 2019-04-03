import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {Quote, QuoteService} from './quote.service';
import {LoginService} from '../user/login.service';

@Component({
    templateUrl:'quote-list.component.html'
})

export class QuoteListComponent implements OnInit{

    quotes: Quote[];
    
    constructor(private router: Router, private service:QuoteService,
        public loginService: LoginService){}
        
    ngOnInit(){
        this.service.findAll(0).subscribe(
            quotes => this.quotes=quotes,
            error => console.log(error)
        );
    }

    /**
     * Botón para añadir una cita, redirección a quote/new 
     * envía a quote-form
     */
    newQuote(){
        this.router.navigate(['/quote/new']);
    }

}