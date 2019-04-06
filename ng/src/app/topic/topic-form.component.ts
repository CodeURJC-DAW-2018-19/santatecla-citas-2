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
<<<<<<< HEAD

    @ViewChild('content') content: ElementRef;
    public downloadPDF(){
        let doc = new jsPDF();

        let specialElementHandlers = {
            '#editor': function(element,rederer){
                return true;
            }

        };

        let content = this.content.nativeElement;
        doc.fromHTML(content.innerHTML,15,15,{
            'width' : 190,
            'elementHandlers' : specialElementHandlers
        });

        doc.save('topic.pdf');
=======
    showPopup(){
        this.router.navigate(['/topic/reference', this.topic.id]);

>>>>>>> 68b5527baae260797cf86d4da176da69487861de
    }
}