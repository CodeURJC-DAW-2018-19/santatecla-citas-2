import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { RequestOptions, Headers, Http } from '@angular/http';
import { Observable } from 'rxjs';

const URL = '/api';
const URLR = '/api/register';

export interface User {
    id?: number;
    userName: string;
    roles: string[];
    password: string;
}

@Injectable()
export class LoginService {

    isLogged = false;
    isAdmin = false;
    user: User;
    auth: string;

    constructor(private http: HttpClient, private httpr: Http) {
        let user = JSON.parse(localStorage.getItem('currentUser'));
        if (user) {
            console.log('Logged user');
            this.setCurrentUser(user);
        }
    }

    logIn(userName: string, pass: string) {

        let auth = window.btoa(userName + ':' + pass);

        const headers = new HttpHeaders({
            Authorization: 'Basic ' + auth,
            'X-Requested-With': 'XMLHttpRequest',
        });

        return this.http.get<User>('/api/login', { headers })
            .pipe(map(user => {

                if (user) {
                    this.setCurrentUser(user);
                    user.password = auth;
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }

                return user;
            }));
    }

    logOut() {

        return this.http.get(URL + '/logout').pipe(
            map(response => {
                this.removeCurrentUser();
                return response;
            }),
        );
    }

    private setCurrentUser(user: User) {
        this.isLogged = true;
        this.user = user;
        this.isAdmin = this.user.roles.indexOf('ROLE_ADMIN') !== -1;
    }

    removeCurrentUser() {
        localStorage.removeItem('currentUser');
        this.isLogged = false;
        this.isAdmin = false;
    }

    register(user: User) {
        const body = JSON.stringify(user);
        const headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'

        });
        console.log(body);
        const options = new RequestOptions({ headers });

<<<<<<< HEAD
        return this.httpr.post(URLR, body, options)
=======
        return this.httpr.post(URLR, body,options)
>>>>>>> 07d2dfa20d1dbd284ba4526b2536f5d85de40a82
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