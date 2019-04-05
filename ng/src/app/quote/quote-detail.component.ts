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
    image: any;

    constructor(private router:Router, activatedRoute: ActivatedRoute,
        public service:QuoteService, public login: LoginService){

            const id = activatedRoute.snapshot.params[`id`];
            service.findOne(id).subscribe(
                quote => {
                    this.quote = quote
                    this.hasImage = quote.imageId!= null;
                    if(this.hasImage){
                service.getImage(id).subscribe(
                    image=>{ 
                        this.image = image;
                        this.createImageFromBlob(this.image);
                    },
                    error => console.error(error)
                );
            }
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

    createImageFromBlob(image: Blob) {
        let reader = new FileReader();
        reader.addEventListener("load", () => {
           this.image = reader.result;
        }, false);
    
        if (image) {
           reader.readAsDataURL(image);
        }
      }

    editQuote(){
        this.router.navigate(['/quote/form', this.quote.id]);
    }



    
}