import { Component } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { QuoteService, Quote } from './quote.service';
import { LoginService } from '../user/login.service';

@Component({
    templateUrl: `quote-detail.component.html`
})
export class QuoteDetailComponent{
    
    quote:Quote;
    hasImage:boolean;

    constructor(private router:Router, activatedRoute: ActivatedRoute,
        public service:QuoteService, public login: LoginService){

            const id = activatedRoute.snapshot.params[`id`];
            service.findOne(id).subscribe(
                quote => {
                    this.quote = quote
                    this.hasImage = quote.imageId!= null;
                },
                error => console.error(error)
            );


        }
    
    removeQuote(){
        const confirm = window.confirm(`Are u sure abut that?`);
        if(confirm){
            this.service.removeQuote(this.quote).subscribe(
                _ => this.router.navigate(['/quotes']),
                error => console.error(error)
                
            )
        }
    }

    editQuote(){
        this.router.navigate(['/quotes/form', this.quote.id]);
    }



    
}