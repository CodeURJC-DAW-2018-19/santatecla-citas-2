import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
var URL = '/api/quotes/';
var QuoteService = /** @class */ (function () {
    function QuoteService(http) {
        this.http = http;
    }
    /**
     * This method should return the page requested,
     * depending on the app state
     */
    QuoteService.prototype.findAll = function (page) {
        var _this = this;
        return this.http.get(URL + "?page=" + page, { withCredentials: true })
            .pipe(map(function (response) { return response.json().content; }), catchError(function (error) { return _this.handleError(error); }));
    };
    QuoteService.prototype.findAllUnpaged = function () {
        var _this = this;
        return this.http.get(URL, { withCredentials: true })
            .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
    };
    QuoteService.prototype.findOne = function (id) {
        var _this = this;
        return this.http.get(URL + id, { withCredentials: true })
            .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
    };
    QuoteService.prototype.postQuote = function (quote) {
        var _this = this;
        var body = JSON.stringify(quote);
        var headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        var options = new RequestOptions({ withCredentials: true, headers: headers });
        if (!quote.id) {
            return this.http.post(URL, body, options)
                .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
        }
        else {
            return this.http.put(URL, body, options)
                .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
        }
    };
    QuoteService.prototype.postImage = function (id, image) {
        var _this = this;
        var body = JSON.stringify(image);
        var headers = new Headers({
            'Content-Type': 'application/x-www-form-urlencoded',
        });
        var options = new RequestOptions({ withCredentials: true, headers: headers });
        //ToDo Ask for how to post this image to the server, json can stringify an image?
        return this.http.post("/api/images/id", image, options)
            .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
        throw new Error("Method not implemented.");
    };
    QuoteService.prototype.removeQuote = function (quote) {
        var _this = this;
        var headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
        });
        var options = new RequestOptions({ withCredentials: true, headers: headers });
        return this.http.delete(URL + "/" + quote.id, options)
            .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
    };
    QuoteService.prototype.handleError = function (error) {
        console.error(error);
        return Observable.throw('Server error(' + error.status + ') ' + error.text);
    };
    QuoteService = tslib_1.__decorate([
        Injectable(),
        tslib_1.__metadata("design:paramtypes", [Http])
    ], QuoteService);
    return QuoteService;
}());
export { QuoteService };
//# sourceMappingURL=quote.service.js.map