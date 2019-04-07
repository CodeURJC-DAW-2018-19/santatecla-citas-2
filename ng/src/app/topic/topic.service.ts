import { Injectable } from '@angular/core'
import { Http, Headers, RequestOptions } from '@angular/http'

import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'
import { Quote, QuoteService } from '../quote/quote.service';
//{} to copy
//@
export interface Topic {
    id?: number;
    name: string;
    nQuotes: number;
    quoteIds: number[];
    texts: string[];
}
const URL = '/api/topics';


@Injectable()
export class TopicService {


    topic: Topic;
    quoteReferenced: Quote[];
    quoteNotReferenced: Quote[];
    private nTopics: number;


    constructor(private http: Http, private quoteService: QuoteService) { }

    /**
     * This method should return the page requested,
     * depending on the app state
     */
    findAll(page: number) {
        return this.http.get(URL + "?page=" + page, { withCredentials: true })
            .pipe(
                map(response => response.json().content),
                catchError(error => this.handleError(error))
            );

    }

    findOne(id: number) {
        return this.http.get(URL + "/" + id, { withCredentials: true })
            .pipe(
                map(response => response.json()),
                catchError(error => this.handleError(error))
            );
    }


    postTopic(topic: Topic) {
        const body = JSON.stringify(topic);
        const headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'

        });
        const options = new RequestOptions({ withCredentials: true, headers });

        if (!topic.id) {
            return this.http.post(URL+'/', body, options)
                .pipe(
                    map(response => response.json()),
                    catchError(error => this.handleError(error))
                );
        } else {
            return this.http.put(URL+'/'+topic.id, body, options)
                .pipe(
                    map(response => response.json()),
                    catchError(error => this.handleError(error))
                );
        }

    }

    removeTopic(topic: Topic) {
        const headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true, headers })
        return this.http.delete(URL + "/" + topic.id, options)
            .pipe(
                map(response => response.json),
                catchError(error => this.handleError(error))
            );

    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error(' + error.status + ') ' + error.text);
    }

    countTopics() {

        return this.http.get(URL, { withCredentials: true })
            .pipe(
                map(response => response = response.json()),
                catchError(error => this.handleError(error))
            );

    }

    removeReference(quote: Quote) {
        var index = this.quoteReferenced.indexOf(quote);
        this.quoteNotReferenced.push(this.quoteReferenced[index]);
        this.quoteReferenced.splice(index, 1);
    }

    addReference(quote: Quote) {
        var index = this.quoteNotReferenced.indexOf(quote);
        this.quoteReferenced.push(this.quoteNotReferenced[index]);
        this.quoteNotReferenced.splice(index, 1);
    }

    getReferences() {
        return this.quoteReferenced;
    }

    getNotReferenced() {
        return this.quoteNotReferenced;
    }

    setReferences(quotes: Quote[]) {
        this.quoteReferenced = quotes;
        this.quoteService.findAllUnpaged().subscribe(
            quotes => {
                this.quoteNotReferenced = quotes
                for (let quote of this.quoteReferenced) {
                    var index = this.quoteNotReferenced.findIndex((current: Quote) => {
                        return current.id == quote.id;
                    });
                    this.quoteNotReferenced.splice(index, 1);
                }
            },
            error => console.log(error)
        );

    }
    setTopic(topic: Topic) {
        this.topic = topic;
    }



}
