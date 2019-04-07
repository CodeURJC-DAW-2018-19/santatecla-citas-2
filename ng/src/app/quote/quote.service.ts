import { Injectable } from '@angular/core'
import { Http, Headers, RequestOptions, ResponseContentType } from '@angular/http'

import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'
//{} to copy
//@
export interface Quote {
    id?: number;
    text: string;
    author: string;
    book: string;
    imageId: number ;

}
const URL = '/api/quotes/';


@Injectable()
export class QuoteService {
    

    constructor(private http: Http) { }

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
    findAllUnpaged() {
        return this.http.get(URL, { withCredentials: true })
            .pipe(
                map(response => response.json()),
                catchError(error => this.handleError(error))
            );
    }

    findOne(id: number) {
        return this.http.get(URL + id, { withCredentials: true })
            .pipe(
                map(response => response.json()),
                catchError(error => this.handleError(error))
            );
    }

    getImage(id: number) {
        return this.http.get('/api/images/'+id, { responseType: ResponseContentType.Blob ,withCredentials: true })
            .pipe(
                map(response => response.blob()),
                catchError(error => this.handleError(error))
            );
    }
    /*private n : number;
    countQuotes() {
        this.http.get(URL, { withCredentials: true })
            .pipe(
                map(response => {
                    let data = response.json();
                    for (var i = 0; i < data.items.length; i++) {
                        this.n++;
                    }
                },
                catchError(error => this.handleError(error))
                )
            )
            console.log(this.n) +"ey";
            return this.n;
    }*/


    postQuote(quote: Quote) {
        const body = JSON.stringify(quote);
        const headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'

        });
        const options = new RequestOptions({ withCredentials: true, headers });

        if (!quote.id) {
            return this.http.post(URL, body, options)
                .pipe(
                    map(response => response.json()),
                    catchError(error => this.handleError(error))
                );
        } else {
            return this.http.put(URL+quote.id, body, options)
                .pipe(
                    map(response => response.json()),
                    catchError(error => this.handleError(error))
                );
        }

    }

    postImage(id: number, image: File) {
        const body = JSON.stringify(image);
        console.log(id);
        const headers = new Headers({
            'Content-Type': 'multipart/form-data',

        });
        let formData: FormData = new FormData();
        formData.append('file', image, image.name);

        const options = new RequestOptions({ withCredentials: true, headers });

        //ToDo Ask for how to post this image to the server, json can stringify an image?
        return this.http.post("/api/images/" + id, formData)
            .pipe(
                map(response => response.json()),
                catchError(error => this.handleError(error))
            );

        throw new Error("Method not implemented.");
    }

    removeQuote(quote: Quote) {
        const headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true, headers })
        return this.http.delete(URL + "/" + quote.id, options)
            .pipe(
                map(response => response.json()),
                catchError(error => this.handleError(error))
            );

    }

    countQuotes(){
        return this.http.get(URL+"/").pipe(
            map(response => response.json()),
            catchError(error => this.handleError(error))
          );   
    }

    private handleError(error: any) {
        console.error(error.Content);
        return Observable.throw('Server error(' + error.status + ') ' + error.text);
    }
}
