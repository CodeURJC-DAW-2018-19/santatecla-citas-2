import {Component} from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import {Topic, TopicService} from './topic.service';
import { Quote } from '../quote/quote.service';

@Component({
    templateUrl: 'topic-form.component.html'
})


export class TopicFormComponent{

    newTopic: boolean;
    topic: Topic;
    hasQuotes: boolean;
    hasText: boolean;

    quotes:Quote[];

    constructor(private router:Router,
        activatedRoute:ActivatedRoute,
        private service: TopicService){

            const id = activatedRoute.snapshot.params['id'];
            if(id){
                service.findOne(id).subscribe(
                    topic => {
                        this.topic = topic;
                        this.hasText = topic.texts != undefined;
                    },
                    error => console.error(error)
                
                );
                this.newTopic = false;
                this.quotes = service.getReferences();

                this.hasQuotes = this.quotes != undefined;
               

            }else{
                this.topic={name:"",nQuotes:0 ,quoteIds:[], texts: []};
                this.newTopic = true;
            }
            
        }

    cancel(){
        window.history.back();
    }

    save(){
        this.service.postTopic(this.topic).subscribe(
            topic=>{},
            error=> console.error('Error creating Topic: '+ error),
        );
        this.router.navigate(['/topics']);
    }

    removeReference(quote:Quote){
        this.service.removeReference(quote);
    }

    showPopup(){
        this.router.navigate(['/topic/reference', this.topic.id]);

    }

    addText(){
        this.topic.texts.push("text");
    }
    trackByIndex(index:number, obj:any){
        return index;
    }
}