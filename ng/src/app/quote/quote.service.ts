import {Injectable} from '@angular/core'
import {Http,Headers, RequestOptions} from '@angular/http'

import {Observable} from 'rxjs';
import {map, catchError} from 'rxjs/operators'
//{} to copy
//@
export interface Quote{
    id?: number;
    text: string;
    author: string;
    book: string;
    
}
const URL ='/api/quotes/';


@Injectable()
export class QuoteService{

    


    constructor(private http: Http){}

    /**
     * This method should return the page requested,
     * depending on the app state
     */
    findAll(page:number){
        return this.http.get(URL+"?="+page,{withCredentials: true})
        .pipe(
            map(response => response.json()),
            catchError(error => this.handleError(error))
            );

    }

    findOne(id:number){
        return this.http.get(URL+id,{withCredentials: true})
        .pipe(
            map(response=>response.json),
            catchError(error=> this.handleError(error))
        );


    }


    postQuote (quote: Quote){
        const body = JSON.stringify(quote);
        const headers = new Headers({
            'Content-Type':'application/json',
            'X-Requested-With':'XMLHttpRequest'

        });
        const options = new RequestOptions({withCredentials:true,headers});

        if(!quote.id){
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

    postImage(id: number, image:File) {
        const body = JSON.stringify(image);
        //ToDo Ask for how to post this image to the server, json can stringify an image?
        return this.http.post()
        throw new Error("Method not implemented.");
    }

    removeQuote(quote:Quote){
        const headers= new Headers({
            'X-Requested-With':'XMLHttpRequest'
        });
        const options = new RequestOptions({withCredentials:true, headers})
        return this.http.delete(URL+"/"+quote.id,options)
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
