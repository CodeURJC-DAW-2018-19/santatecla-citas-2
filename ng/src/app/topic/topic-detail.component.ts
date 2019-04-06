import { Component, Input } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { TopicService, Topic } from './topic.service';
import { LoginService } from '../user/login.service';
import { Quote, QuoteService } from '../quote/quote.service';

@Component({
    templateUrl: `topic-detail.component.html`
})
export class TopicDetailComponent {

    topic: Topic;
    quotes: Quote[];
    quoteNumbers: number[];

    constructor(private router: Router, activatedRoute: ActivatedRoute,
        public service: TopicService, public quoteService: QuoteService, public login: LoginService) {

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

    ngOnInit() {
            
        /*
        for(let id of this.quoteNumbers){
            this.quoteService.findOne(id).subscribe(
                quote => this.quotes.push(quote),
                error => console.error(error)
            )
        };*/
    }

    removeQuote() {
        const confirm = window.confirm(`Are u sure abut that?`);
        if (confirm) {
            this.service.removeQuote(this.topic).subscribe(
                _ => this.router.navigate(['/quotes']),
                error => console.error(error)

            )
        }
    }

    editQuote() {
        this.router.navigate(['/quotes/form', this.topic.id]);
    }




}