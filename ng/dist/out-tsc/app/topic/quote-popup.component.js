import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { QuoteService } from '../quote/quote.service';
import { LoginService } from '../user/login.service';
var QuotePopupComponent = /** @class */ (function () {
    function QuotePopupComponent(router, service, loginService) {
        this.router = router;
        this.service = service;
        this.loginService = loginService;
    }
    QuotePopupComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.service.findAllUnpaged().subscribe(function (quotes) { return _this.quotes = quotes; }, function (error) { return console.log(error); });
    };
    QuotePopupComponent = tslib_1.__decorate([
        Component({
            templateUrl: '../quote/quote-list.component.html'
        }),
        tslib_1.__metadata("design:paramtypes", [Router, QuoteService,
            LoginService])
    ], QuotePopupComponent);
    return QuotePopupComponent;
}());
export { QuotePopupComponent };
//# sourceMappingURL=quote-popup.component.js.map