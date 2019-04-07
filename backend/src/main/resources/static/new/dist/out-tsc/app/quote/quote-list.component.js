import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { QuoteService } from './quote.service';
import { LoginService } from '../user/login.service';
var QuoteListComponent = /** @class */ (function () {
    function QuoteListComponent(router, service, loginService) {
        this.router = router;
        this.service = service;
        this.loginService = loginService;
    }
    QuoteListComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.service.findAll(0).subscribe(function (quotes) { return _this.quotes = quotes; }, function (error) { return console.log(error); });
    };
    /**
     * Botón para añadir una cita, redirección a quote/new
     * envía a quote-form
     */
    QuoteListComponent.prototype.newQuote = function () {
        this.router.navigate(['/quote/new']);
    };
    QuoteListComponent = tslib_1.__decorate([
        Component({
            templateUrl: 'quote-list.component.html'
        }),
        tslib_1.__metadata("design:paramtypes", [Router, QuoteService,
            LoginService])
    ], QuoteListComponent);
    return QuoteListComponent;
}());
export { QuoteListComponent };
//# sourceMappingURL=quote-list.component.js.map