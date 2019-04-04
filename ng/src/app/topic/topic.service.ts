import {Injectable, Output, EventEmitter} from '@angular/core'
import {Http,Headers, RequestOptions} from '@angular/http'

import {Observable} from 'rxjs';
import {map, catchError} from 'rxjs/operators'
import { Quote } from '../quote/quote.service';
//{} to copy
//@
export interface Topic{
    id?: number;
    name: string;
    nQuotes:number;
    quoteIds: number[];
    texts: string[];
    
    
}
const URL ='/api/topics';


@Injectable()
export class TopicService{

    quoteReferenced: Quote[];
    quoteNotReferenced: Quote[];


    constructor(private http: Http){}

    /**
     * This method should return the page requested,
     * depending on the app state
     */
    findAll(page:number){
        return this.http.get(URL+"?page="+page,{withCredentials: true})
        .pipe(
            map(response => response.json().content),
            catchError(error => this.handleError(error))
            );

    }

    findOne(id:number){
        return this.http.get(URL+"/"+id,{withCredentials: true})
        .pipe(
            map(response=>response.json().content),
            catchError(error=> this.handleError(error))
        );


    }


    postQuote (topic: Topic){
        const body = JSON.stringify(topic);
        const headers = new Headers({
            'Content-Type':'application/json',
            'X-Requested-With':'XMLHttpRequest'

        });
        const options = new RequestOptions({withCredentials:true,headers});

        if(!topic.id){
            return this.http.post(URL,body,options)
            .pipe(
                map(response=>response.json()),
                catchError(error=>this.handleError(error))
            );
        }else{
            return this.http.put(URL,body,options)
            .pipe(
                map(response=>response.json()),
                catchError(error=>this.handleError(error))
            );
        }

    }

    removeQuote(topic: Topic){
        const headers= new Headers({
            'X-Requested-With':'XMLHttpRequest'
        });
        const options = new RequestOptions({withCredentials:true, headers})
        return this.http.delete(URL+"/"+topic.id,options)
        .pipe(
            map(response=>response.json),
            catchError(error=> this.handleError(error))
        );

    }

    private handleError(error:any){
        console.error(error);
        return Observable.throw('Server error('+error.status +') '+error.text);
    }

    
}
