import * as tslib_1 from "tslib";
import { Component } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { QuoteService } from './quote.service';
import { LoginService } from '../user/login.service';
var QuoteDetailComponent = /** @class */ (function () {
    function QuoteDetailComponent(router, activatedRoute, service, login) {
        var _this = this;
        this.router = router;
        this.service = service;
        this.login = login;
        var id = activatedRoute.snapshot.params["id"];
        service.findOne(id).subscribe(function (quote) { return _this.quote = quote; }, function (error) { return console.error(error); });
    }
    QuoteDetailComponent.prototype.removeQuote = function () {
        var _this = this;
        var confirm = window.confirm("Are u sure abut that?");
        if (confirm) {
            this.service.removeQuote(this.quote).subscribe(function (_) { return _this.router.navigate(['/quotes']); }, function (error) { return console.error(error); });
        }
    };
    QuoteDetailComponent.prototype.editQuote = function () {
        this.router.navigate(['/quotes/form', this.quote.id]);
    };
    QuoteDetailComponent = tslib_1.__decorate([
        Component({
            templateUrl: "quote-detail.component.html"
        }),
        tslib_1.__metadata("design:paramtypes", [Router, ActivatedRoute,
            QuoteService, LoginService])
    ], QuoteDetailComponent);
    return QuoteDetailComponent;
}());
export { QuoteDetailComponent };
//# sourceMappingURL=quote-detail.component.js.map