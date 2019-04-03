import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
var URL = '/api/topics';
var TopicService = /** @class */ (function () {
    function TopicService(http) {
        this.http = http;
    }
    /**
     * This method should return the page requested,
     * depending on the app state
     */
    TopicService.prototype.findAll = function (page) {
        var _this = this;
        return this.http.get(URL + "?page=" + page, { withCredentials: true })
            .pipe(map(function (response) { return response.json().content; }), catchError(function (error) { return _this.handleError(error); }));
    };
    TopicService.prototype.findOne = function (id) {
        var _this = this;
        return this.http.get(URL + "/" + id, { withCredentials: true })
            .pipe(map(function (response) { return response.json().content; }), catchError(function (error) { return _this.handleError(error); }));
    };
    TopicService.prototype.postQuote = function (topic) {
        var _this = this;
        var body = JSON.stringify(topic);
        var headers = new Headers({
            'Content-Type': 'application/json',
            'X-Requested-With': 'XMLHttpRequest'
        });
        var options = new RequestOptions({ withCredentials: true, headers: headers });
        if (!topic.id) {
            return this.http.post(URL, body, options)
                .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
        }
        else {
            return this.http.put(URL, body, options)
                .pipe(map(function (response) { return response.json(); }), catchError(function (error) { return _this.handleError(error); }));
        }
    };
    TopicService.prototype.removeQuote = function (topic) {
        var _this = this;
        var headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
        });
        var options = new RequestOptions({ withCredentials: true, headers: headers });
        return this.http.delete(URL + "/" + topic.id, options)
            .pipe(map(function (response) { return response.json; }), catchError(function (error) { return _this.handleError(error); }));
    };
    TopicService.prototype.handleError = function (error) {
        console.error(error);
        return Observable.throw('Server error(' + error.status + ') ' + error.text);
    };
    TopicService = tslib_1.__decorate([
        Injectable(),
        tslib_1.__metadata("design:paramtypes", [Http])
    ], TopicService);
    return TopicService;
}());
export { TopicService };
//# sourceMappingURL=topic.service.js.map