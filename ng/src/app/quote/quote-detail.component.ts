import { Component } from "@angular/core";
import { Quote } from '@angular/compiler';
import { Router, ActivatedRoute } from '@angular/router';
import { QuoteService } from './quote.service';
import { LoginService } from '../user/login.service';

@Component({
    templateUrl: `quote-detail.component.html`
})
export class QuoteDetailComponent{
    quote:Quote;

    constructor(private router:Router, activatedRoute: ActivatedRoute,
        public service:QuoteService, public login: LoginService){

            const id = activatedRoute.snapshot.params[`id`];
            service
        }
}