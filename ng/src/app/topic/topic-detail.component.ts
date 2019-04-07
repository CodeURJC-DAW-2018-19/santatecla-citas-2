import { Component, Input } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { TopicService, Topic } from './topic.service';
import { LoginService } from '../user/login.service';
import { Quote, QuoteService } from '../quote/quote.service';
//import * as jsPDF from 'jspdf';

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

    removeTopic() {
        const confirm = window.confirm(`Are u sure abut that?`);
        if (confirm) {
            this.service.removeTopic(this.topic).subscribe(
                _ => this.router.navigate(['/topics']),
                error => console.error(error)

            )
        }

        this.router.navigate(['/topics']);
    }

    editTopic() {
        this.service.setTopic(this.topic);
        this.service.setReferences(this.quotes);
        this.router.navigate(['/topic/form', this.topic.id]);
    }

    downloadPDF(){
        let doc =new jsPDF();
        doc.text(this.topic.name,10,10);
        doc.text(this.topic.texts,10,10);
        for(let quote of this.quotes){
            doc.text(quote.text,10,10);
            doc.text(quote.book,10,10);
            doc.text(quote.author,10,10);
        }
        doc.save('Tema'+this.topic.name);


    }




}