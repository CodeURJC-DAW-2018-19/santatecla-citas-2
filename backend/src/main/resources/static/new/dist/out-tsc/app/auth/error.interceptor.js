import * as tslib_1 from "tslib";
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LoginService } from '../user/login.service';
var ErrorInterceptor = /** @class */ (function () {
    function ErrorInterceptor(loginService) {
        this.loginService = loginService;
    }
    ErrorInterceptor.prototype.intercept = function (request, next) {
        return next.handle(request).pipe(catchError(function (err) {
            if (err.status === 401) {
                // auto logout if 401 response returned from api
                //  this.loginService.removeCurrentUser();
                //location.reload(true);
            }
            return throwError(err);
        }));
    };
    ErrorInterceptor = tslib_1.__decorate([
        Injectable(),
        tslib_1.__metadata("design:paramtypes", [LoginService])
    ], ErrorInterceptor);
    return ErrorInterceptor;
}());
export { ErrorInterceptor };
//# sourceMappingURL=error.interceptor.js.map