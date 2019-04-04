import * as tslib_1 from "tslib";
import { Component } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { TopicService } from './topic.service';
import { LoginService } from '../user/login.service';
import { QuoteService } from '../quote/quote.service';
var TopicDetailComponent = /** @class */ (function () {
    function TopicDetailComponent(router, activatedRoute, service, quoteService, login) {
        var _this = this;
        this.router = router;
        this.service = service;
        this.quoteService = quoteService;
        this.login = login;
        var id = activatedRoute.snapshot.params["id"];
        service.findOne(id).subscribe(function (topic) { return _this.topic = topic; }, function (error) { return console.error(error); });
        //This for loads each quote related to the topic
        console.log(this.topic);
        /* for(let id of this.topic.quoteIds){
             quoteService.findOne(id).subscribe(
                 quote => this.quotes.push(quote),
                 error => console.error(error)
             )
         }*/
    }
    TopicDetailComponent.prototype.removeQuote = function () {
        var _this = this;
        var confirm = window.confirm("Are u sure abut that?");
        if (confirm) {
            this.service.removeQuote(this.topic).subscribe(function (_) { return _this.router.navigate(['/quotes']); }, function (error) { return console.error(error); });
        }
    };
    TopicDetailComponent.prototype.editQuote = function () {
        this.router.navigate(['/quotes/form', this.topic.id]);
    };
    TopicDetailComponent = tslib_1.__decorate([
        Component({
            templateUrl: "topic-detail.component.html"
        }),
        tslib_1.__metadata("design:paramtypes", [Router, ActivatedRoute,
            TopicService, QuoteService, LoginService])
    ], TopicDetailComponent);
    return TopicDetailComponent;
}());
export { TopicDetailComponent };
//# sourceMappingURL=topic-detail.component.js.map