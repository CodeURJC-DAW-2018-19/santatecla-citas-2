import { Component } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { TopicService, Topic } from './topic.service';
import { LoginService } from '../user/login.service';
import *as jsPDF from 'jspdf';


@Component({
    templateUrl: `quote-detail.component.html`
})
export class TopicDetailComponent{
    
    topic:Topic;

    constructor(private router:Router, activatedRoute: ActivatedRoute,
        public service:TopicService, public login: LoginService){

        this.quotes= new Array();
            const id = activatedRoute.snapshot.params[`id`];
            service.findOne(id).subscribe(
            topic => {
                this.topic = topic
                for(var i=0 ;i<topic.nQuotes;i++){
                    quoteService.findOne(topic.quoteIds[i]).subscribe(
                        quote => this.quotes.push(quote),
                        error => console.log(error)
                    )
                }
            },
            error => console.log(error)
        );
        //console.log(this.topic.name);
        }
    
    removeQuote(){
        const confirm = window.confirm(`Are u sure abut that?`);
        if(confirm){
            this.service.removeQuote(this.topic).subscribe(
                _ => this.router.navigate(['/quotes']),
                error => console.error(error)
                
            )
        }
    }

    editQuote(){
        this.router.navigate(['/quotes/form', this.topic.id]);
    }

    editTopic() {
        this.service.setTopic(this.topic);
        this.service.setReferences(this.quotes);
        this.router.navigate(['/topic/form', this.topic.id]);
    }




}