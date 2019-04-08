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
    texts: string[];

    quotes:Quote[];

    constructor(private router:Router,
        activatedRoute:ActivatedRoute,
        private service: TopicService){

            const id = activatedRoute.snapshot.params['id'];
            if(id && id != 0){
                service.findOne(id).subscribe(
                    topic => {
                        this.topic = topic;
                        this.service.setReferences(topic.quoteIds);
                        this.hasText = topic.texts != undefined;
                        if(this.hasText){
                            this.texts=topic.texts;
                        }else{
                            this.texts = new Array();
                        }
                    },
                    error => console.error(error)
                
                );
                this.newTopic = false;

                
               

            }else if(!id){
                this.topic={name:"",nQuotes:0 ,quoteIds:[], texts: []};
                this.service.setReferences(this.topic.quoteIds);
                this.newTopic = true;
                this.texts = new Array();
            }
            if(id == 0){
                this.topic = service.getTopic();
                this.texts = this.topic.texts;
                this.hasText = this.topic.texts!= undefined;
            }

            this.quotes = service.getReferences();
            this.hasQuotes = this.quotes != undefined;
        }

    cancel(){
        window.history.back();
    }

    save(){
        this.topic.texts= this.texts;
        this.topic.quoteIds = new Array();
        if(this.hasQuotes){
            for(let quote of this.quotes){
               this.topic.quoteIds.push(quote.id);
            }
            this.topic.nQuotes= this.quotes.length;
        }
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
        this.topic.texts = this.texts;
        this.service.setTopic(this.topic);
        var id = 0;
        if(this.topic.id !=undefined){
            id = this.topic.id;
        }
        this.router.navigate(['/topic/reference', id]);

    }

    addText(){
        this.texts.push('text');
        this.hasText = true;
    }

    trackByIndex(index:number, obj:any){
        return index;
    }
}