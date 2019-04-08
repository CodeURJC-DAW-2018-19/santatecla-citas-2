import {Component, OnInit, Input} from '@angular/core';
import {Router} from '@angular/router';

import {Quote, QuoteService} from '../quote/quote.service';
import {LoginService} from '../user/login.service';
import { TopicService } from './topic.service';

@Component({
    templateUrl:'../quote/quote-list.component.html'
})

export class QuotePopupComponent implements OnInit{

    reference: boolean = true;
    quotes: Quote[];
    
    

    constructor(private router: Router, private service:TopicService, private quoteService:QuoteService,
        public loginService: LoginService){
            this.quotes = this.service.getNotReferenced();
        }
        
    ngOnInit(){
       this.quotes = this.service.getNotReferenced();
    }

    /**
     * Botón para añadir una cita, redirección a quote/new 
     * envía a quote-form
     */
    
     addReference(quote:Quote){
        this.service.addReference(quote);
        window.history.back();
        //Send quote to topicFormComponent so it adds this quote to the referenced ones
     }
     

}