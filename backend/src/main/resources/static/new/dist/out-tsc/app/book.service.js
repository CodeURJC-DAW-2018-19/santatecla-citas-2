import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
var URL = '/api/books/';
var BookService = /** @class */ (function () {
    function BookService(http) {
        this.http = http;
    }
    BookService.prototype.getBooks = function () {
        var _this = this;
        return this.http.get(URL, { withCredentials: true })
            .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
    };
    BookService.prototype.getBook = function (id) {
        var _this = this;
        return this.http.get(URL + id, { withCredentials: true })
            .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
    };
    BookService.prototype.saveBook = function (book) {
        var _this = this;
        var body = JSON.stringify(book);
        var headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        var options = new RequestOptions({ withCredentials: true, headers: headers });
        if (!book.id) {
            return this.http.post(URL, body, options)
                .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
        }
        else {
            return this.http.put(URL + book.id, body, options)
                .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
        }
    };
    BookService.prototype.removeBook = function (book) {
        var _this = this;
        var headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
        });
        var options = new RequestOptions({ withCredentials: true, headers: headers });
        return this.http.delete(URL + book.id, options)
            .pipe(map(function (response) { return undefined; }), catchError(function (error) { return _this.handleError(error); }));
    };
    BookService.prototype.updateBook = function (book) {
        var _this = this;
        var body = JSON.stringify(book);
        var headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        var options = new RequestOptions({ withCredentials: true, headers: headers });
        return this.http.put(URL + book.id, body, options)
            .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
    };
    BookService.prototype.handleError = function (error) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    };
    BookService = tslib_1.__decorate([
        Injectable(),
        tslib_1.__metadata("design:paramtypes", [Http])
    ], BookService);
    return BookService;
}());
export { BookService };
//# sourceMappingURL=book.service.js.map