import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {Topic, TopicService} from './topic.service';
import {LoginService} from '../login.service';

@Component({
    templateUrl:'quote-list.component.html'
})

export class TopicListComponent implements OnInit{

    topics: Topic[];
    
    constructor(private router: Router, private service:TopicService,
        public loginService: LoginService){}
        
    ngOnInit(){
        this.service.findAll(0).subscribe(
            topics => this.topics=topics,
            error => console.log(error)
        );
    }

    /**
     * Duda, para que sirve??
     */
    newQuote(){
        this.router.navigate(['/topic/new']);
    }

}