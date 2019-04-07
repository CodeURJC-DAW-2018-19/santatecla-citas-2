import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators'

export interface User {
  id?: number;
  email: string;
  passwar: string;
}

const URL = '/api/users/';
@Injectable()
export class UserService {

  constructor(private http: Http) { }

  getUser() {
    return this.http.get(URL, { withCredentials: true })
      .pipe(
        map(response => response.json()),
        catchError(error => this.handleError(error))
      );
  }

  getuser(id: number |string) {
    return this.http.get(URL + id, { withCredentials: true })
      .pipe(
          map(response => response.json()),
          catchError(error => this.handleError(error))
      );
  }

  addUser(user: User) {

    const body = JSON.stringify(user);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    if (!user.id) {
      return this.http.post(URL, body, options)
        .pipe(
          map(response => response.json()),
          catchError(error => this.handleError(error))
        );
    } else {
      return this.http.put(URL + user.id, body, options)
        .pipe(
          map(response => response.json()),
          catchError(error => this.handleError(error)
        ));
    }
  }

  removeUser(user: User) {

    const headers = new Headers({
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.delete(URL + user.id, options)
      .pipe(
        map(response => undefined),
        catchError(error => this.handleError(error))
      );
  }

  updateBook(user: User) {

    const body = JSON.stringify(user);
    const headers = new Headers({
      'Content-Type': 'application/json',
      'X-Requested-With': 'XMLHttpRequest'
    });
    const options = new RequestOptions({ withCredentials: true, headers });

    return this.http.put(URL + user.id, body, options)
      .pipe(
        map(response => response.json()), 
        catchError(error => this.handleError(error)),
      );
  }

  private handleError(error: any) {
    console.error(error);
    return Observable.throw('Server error (' + error.status + '): ' + error.text());
  }
}
