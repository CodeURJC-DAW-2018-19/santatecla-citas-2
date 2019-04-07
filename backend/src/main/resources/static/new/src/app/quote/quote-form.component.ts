import {Component} from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import {Quote, QuoteService} from './quote.service';

@Component({
    templateUrl: `quote-form.component.html`
})
export class QuoteFormComponent{
    newQuote: boolean;
    quote: Quote;
    file: File;
    codedFile: string;
    id:number;

    constructor(private router:Router,
        activatedRoute:ActivatedRoute,
        private service: QuoteService){

            this.id = activatedRoute.snapshot.params['id'];
            if(this.id){
                service.findOne(this.id).subscribe(
                    quote => this.quote = quote,
                    error => console.error(error)
                );
                this.newQuote = false;

            }else{
                this.quote = {author:"",text:'',book:'', imageId:null};
                this.newQuote = true;
            }
        }

    cancel(){
        window.history.back();
    }

    save(){
        this.service.postQuote(this.quote).subscribe(
            quote => {this.quote=quote;
                this.service.postImage(this.quote.id,this.file).subscribe(
                file =>{},
                error => console.error('Error uploading image: '+error),
                
                
            );
        },
            error=> console.error('Error creating quote: '+ error),
        );
        
        window.history.back();

    }

    onFileChanged(event){
        this.file = event.target.files[0];
        
    }
}