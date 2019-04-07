import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
// Strategy based on http://jasonwatmore.com/post/2018/09/07/angular-6-basic-http-authentication-tutorial-example
var BasicAuthInterceptor = /** @class */ (function () {
    function BasicAuthInterceptor() {
    }
    BasicAuthInterceptor.prototype.intercept = function (request, next) {
        // add authorization header with basic auth credentials if available
        var user = JSON.parse(localStorage.getItem('currentUser'));
        if (user && user.authdata) {
            request = request.clone({
                setHeaders: {
                    Authorization: "Basic " + user.authdata
                }
            });
        }
        return next.handle(request);
    };
    BasicAuthInterceptor = tslib_1.__decorate([
        Injectable()
    ], BasicAuthInterceptor);
    return BasicAuthInterceptor;
}());
export { BasicAuthInterceptor };
//# sourceMappingURL=auth.interceptor.js.map