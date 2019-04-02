import { Component } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { TopicService, Topic } from './topic.service';
import { LoginService } from '../user/login.service';

@Component({
    templateUrl: `topic-detail.component.html`
})
export class TopicDetailComponent{
    
    topic:Topic;

    constructor(private router:Router, activatedRoute: ActivatedRoute,
        public service:TopicService, public login: LoginService){

            const id = activatedRoute.snapshot.params[`id`];
            service.findOne(id).subscribe(
                topic => this.topic = topic,
                error => console.error(error)
            );
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



    
}