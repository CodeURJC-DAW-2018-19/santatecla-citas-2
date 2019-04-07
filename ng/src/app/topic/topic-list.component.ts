import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {Topic, TopicService} from './topic.service';
import {LoginService} from '../user/login.service';
import { IPageChangeEvent } from '@covalent/core';

@Component({
    selector: 'topic-list',
    templateUrl:'topic-list.component.html'
})

export class TopicListComponent implements OnInit{

    topics: Topic[];
    numTopics : number;
    event: IPageChangeEvent;
    firstLast: boolean = true;
    pageSize = 10;
    pageNumber = 1;
    firstPage = 0;
    total : number;
    pageChanged: boolean = false;
    
    constructor(private router: Router, private service:TopicService,
        public loginService: LoginService){}
        
    ngOnInit(){
        this.service.findAll(0).subscribe(
            topics => this.topics=topics,
            error => console.log(error)
        );
        this.service.countTopics().subscribe(
            data => this.total = Object.keys(data).length,
            error => console.log(error)
        );
    }

    /**
     * Duda, para que sirve??
     */
    newTopic(){
        this.router.navigate(['topic/new']);
    }

    ngDoCheck() {
        if (this.pageChanged) {
            this.service.findAll(this.pageNumber - 1).subscribe(
                topics => this.topics = topics,
                error => console.log(error)
            );
            this.pageChanged = false;
        }

    }

    change(event: IPageChangeEvent): void {
        this.event = event;
        this.pageNumber = event.page;
        this.pageChanged = true;
    }

    toggleFirstLast(): void {
        this.firstLast = !this.firstLast;
    }

}