import { Injectable } from '@angular/core';
import { Http, ResponseContentType } from '@angular/http';
import { map, catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';

export interface Chart {
    id?: number;
    name: string;
    valor: number;
}
const URL = '/api/chart';

@Injectable()
export class ChartService {

    constructor(private http : Http){
    }

    getChart() {
        return this.http.get(URL, {withCredentials: true })
            .pipe(
                map(response => response.json()),
                catchError(error => this.handleError(error))
        );
    }

    private handleError(error: any) {
        console.error(error.Content);
        return Observable.throw('Server error(' + error.status + ') ' + error.text);
    }

}