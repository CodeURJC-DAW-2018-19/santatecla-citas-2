import {Component} from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import {Quote, QuoteService} from './quote.service';

@Component({
    templateUrl: 'quote-form.component.html'
})
export class QuoteFormComponent{
    newQuote: boolean;
    quote: Quote;

    constructor(private router:Router,
        activatedRoute:ActivatedRoute,
        private service: QuoteService){

            const id = activatedRoute.snapshot.params['id'];
            if(id){
                service.findOne(id).subscribe(
                    quote => this.quote = quote,
                    error => console.error(error)
                );
                this.newQuote = false;

            }else{
                this.quote = {author:"",text:'',book:''};
                this.newQuote = true;
            }
        }

    cancel(){
        window.history.back();
    }

    save(){
        this.service.postQuote(this.quote).subscribe(
            quote=>{},
            error=> console.error('Error creating quote: '+ error),
        );
        window.history.back();
    }
}