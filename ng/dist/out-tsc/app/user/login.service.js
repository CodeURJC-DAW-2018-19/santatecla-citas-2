import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import { map } from 'rxjs/operators';
var URL = '/api';
var LoginService = /** @class */ (function () {
    function LoginService(http) {
        this.http = http;
        this.isLogged = false;
        this.isAdmin = false;
        this.reqIsLogged();
    }
    LoginService.prototype.reqIsLogged = function () {
        var _this = this;
        var headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
        });
        var options = new RequestOptions({ withCredentials: true, headers: headers });
        this.http.get(URL + '/login', options).subscribe(function (response) { return _this.processLogInResponse(response); }, function (error) {
            if (error.status !== 401) {
                console.error('Error when asking if logged: ' +
                    JSON.stringify(error));
            }
        });
    };
    LoginService.prototype.processLogInResponse = function (response) {
        this.isLogged = true;
        this.user = response.json();
        this.isAdmin = this.user.roles.indexOf('ROLE_ADMIN') !== -1;
    };
    LoginService.prototype.logIn = function (user, pass) {
        var _this = this;
        console.log('user:' + user + ' pass:' + pass);
        var userPass = user + ':' + pass;
        var headers = new Headers({
            'Authorization': 'Basic ' + utf8_to_b64(userPass),
            'X-Requested-With': 'XMLHttpRequest'
        });
        var options = new RequestOptions({ withCredentials: true, headers: headers });
        return this.http.get(URL + '/login', options).pipe(map(function (response) {
            _this.processLogInResponse(response);
            return _this.user;
        }));
    };
    LoginService.prototype.logOut = function () {
        var _this = this;
        return this.http.get(URL + '/logout', { withCredentials: true }).pipe(map(function (response) {
            _this.isLogged = false;
            _this.isAdmin = false;
            return response;
        }));
    };
    LoginService = tslib_1.__decorate([
        Injectable(),
        tslib_1.__metadata("design:paramtypes", [Http])
    ], LoginService);
    return LoginService;
}());
export { LoginService };
function utf8_to_b64(str) {
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {
        return String.fromCharCode('0x' + p1);
    }));
}
//# sourceMappingURL=login.service.js.map