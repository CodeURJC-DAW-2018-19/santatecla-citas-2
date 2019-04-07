import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
var URL = '/api/users/';
var UserService = /** @class */ (function () {
    function UserService(http) {
        this.http = http;
    }
    UserService.prototype.getUser = function () {
        var _this = this;
        return this.http.get(URL, { withCredentials: true })
            .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
    };
    UserService.prototype.getuser = function (id) {
        var _this = this;
        return this.http.get(URL + id, { withCredentials: true })
            .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
    };
    UserService.prototype.addUser = function (user) {
        var _this = this;
        var body = JSON.stringify(user);
        var headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        var options = new RequestOptions({ withCredentials: true, headers: headers });
        if (!user.id) {
            return this.http.post(URL, body, options)
                .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
        }
        else {
            return this.http.put(URL + user.id, body, options)
                .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
        }
    };
    UserService.prototype.removeUser = function (user) {
        var _this = this;
        var headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
        });
        var options = new RequestOptions({ withCredentials: true, headers: headers });
        return this.http.delete(URL + user.id, options)
            .pipe(map(function (response) { return undefined; }), catchError(function (error) { return _this.handleError(error); }));
    };
    UserService.prototype.updateBook = function (user) {
        var _this = this;
        var body = JSON.stringify(user);
        var headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        var options = new RequestOptions({ withCredentials: true, headers: headers });
        return this.http.put(URL + user.id, body, options)
            .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
    };
    UserService.prototype.handleError = function (error) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    };
    UserService = tslib_1.__decorate([
        Injectable(),
        tslib_1.__metadata("design:paramtypes", [Http])
    ], UserService);
    return UserService;
}());
export { UserService };
//# sourceMappingURL=user.service.js.map