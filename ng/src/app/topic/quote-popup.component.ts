import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {Quote, QuoteService} from '../quote/quote.service';
import {LoginService} from '../user/login.service';

@Component({
    templateUrl:'../quote/quote-list.component.html'
})

export class QuotePopupComponent implements OnInit{

    quotes: Quote[];
    reference: boolean = true;
    
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
    
     addReference(quote:Quote){

        var index = this.quotes.indexOf(quote);
        this.quotes.splice(index, 1);
        window.history.back();
        //Send quote to topicFormComponent so it adds this quote to the referenced ones
     }

}