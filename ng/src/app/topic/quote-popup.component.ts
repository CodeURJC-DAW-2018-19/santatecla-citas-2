import {Component, OnInit, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {Quote, QuoteService} from '../quote/quote.service';
import {LoginService} from '../user/login.service';
import { TopicService } from './topic.service';

@Component({
    templateUrl:'../quote/quote-list.component.html'
})

export class QuotePopupComponent implements OnInit{

    reference: boolean = true;
    quotes: Quote[];
    id:number;
    
    

    constructor(private router: Router, activatedRoute:ActivatedRoute, private service:TopicService, private quoteService:QuoteService,
        public loginService: LoginService){

            this.id = activatedRoute.snapshot.params['id'];

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
        this.router.navigate(['topic/form',this.id]);
        //Send quote to topicFormComponent so it adds this quote to the referenced ones
     }
     

}