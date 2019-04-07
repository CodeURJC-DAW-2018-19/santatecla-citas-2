import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { QuoteService } from './quote.service';
var QuoteFormComponent = /** @class */ (function () {
    function QuoteFormComponent(router, activatedRoute, service) {
        var _this = this;
        this.router = router;
        this.service = service;
        var id = activatedRoute.snapshot.params['id'];
        if (id) {
            service.findOne(id).subscribe(function (quote) { return _this.quote = quote; }, function (error) { return console.error(error); });
            this.newQuote = false;
        }
        else {
            this.quote = { author: "", text: '', book: '' };
            this.newQuote = true;
        }
    }
    QuoteFormComponent.prototype.cancel = function () {
        window.history.back();
    };
    QuoteFormComponent.prototype.save = function () {
        this.service.postQuote(this.quote).subscribe(function (quote) { }, function (error) { return console.error('Error creating quote: ' + error); });
        this.service.postImage(this.quote.id, this.file).subscribe(function (file) { }, function (error) { return console.error('Error uploading image: ' + error); });
        window.history.back();
    };
    QuoteFormComponent.prototype.onFileChanged = function (event) {
        this.file = event.target.files[0];
    };
    QuoteFormComponent = tslib_1.__decorate([
        Component({
            templateUrl: "quote-form.component.html"
        }),
        tslib_1.__metadata("design:paramtypes", [Router,
            ActivatedRoute,
            QuoteService])
    ], QuoteFormComponent);
    return QuoteFormComponent;
}());
export { QuoteFormComponent };
//# sourceMappingURL=quote-form.component.js.map