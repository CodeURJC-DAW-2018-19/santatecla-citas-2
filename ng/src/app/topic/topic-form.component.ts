import {Component} from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import {Topic, TopicService} from './topic.service';
import { Quote } from '../quote/quote.service';

@Component({
    selector: 'topic-form',
    templateUrl: 'topic-form.component.html'
})
export class TopicFormComponent{
    newTopic: boolean;
    topic: Topic;

    quotes:Quote[];

    constructor(private router:Router,
        activatedRoute:ActivatedRoute,
        private service: TopicService){

            const id = activatedRoute.snapshot.params['id'];
            if(id){
                service.findOne(id).subscribe(
                    topic => this.topic = topic,
                    error => console.error(error)
                );
                this.newTopic = false;

            }
            this.quotes= service.getReferences();
        }

    cancel(){
        window.history.back();
    }
    
    save(){
        this.service.postQuote(this.topic).subscribe(
            topic=>{},
            error=> console.error('Error creating quote: '+ error),
        );
        this.router.navigate(['/topics']);
    }

    removeReference(quote:Quote){
        this.service.removeReference(quote);
    }

    showPopup(){
        this.router.navigate(['/topic/reference', this.topic.id]);

    }
}