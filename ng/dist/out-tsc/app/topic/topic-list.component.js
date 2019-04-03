import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TopicService } from './topic.service';
import { LoginService } from '../user/login.service';
var TopicListComponent = /** @class */ (function () {
    function TopicListComponent(router, service, loginService) {
        this.router = router;
        this.service = service;
        this.loginService = loginService;
    }
    TopicListComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.service.findAll(0).subscribe(function (topics) { return _this.topics = topics; }, function (error) { return console.log(error); });
    };
    /**
     * Duda, para que sirve??
     */
    TopicListComponent.prototype.newQuote = function () {
        this.router.navigate(['/topic/new']);
    };
    TopicListComponent = tslib_1.__decorate([
        Component({
            templateUrl: 'topic-list.component.html'
        }),
        tslib_1.__metadata("design:paramtypes", [Router, TopicService,
            LoginService])
    ], TopicListComponent);
    return TopicListComponent;
}());
export { TopicListComponent };
//# sourceMappingURL=topic-list.component.js.map