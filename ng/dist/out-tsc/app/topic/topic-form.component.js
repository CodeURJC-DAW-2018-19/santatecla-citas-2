import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { TopicService } from './topic.service';
var TopicFormComponent = /** @class */ (function () {
    function TopicFormComponent(router, activatedRoute, service) {
        var _this = this;
        this.router = router;
        this.service = service;
        var id = activatedRoute.snapshot.params['id'];
        if (id) {
            service.findOne(id).subscribe(function (topic) { return _this.topic = topic; }, function (error) { return console.error(error); });
            this.newTopic = false;
        }
    }
    TopicFormComponent.prototype.cancel = function () {
        window.history.back();
    };
    TopicFormComponent.prototype.save = function () {
        this.service.postQuote(this.topic).subscribe(function (topic) { }, function (error) { return console.error('Error creating quote: ' + error); });
        window.history.back();
    };
    TopicFormComponent = tslib_1.__decorate([
        Component({
            templateUrl: 'topic-form.component.html'
        }),
        tslib_1.__metadata("design:paramtypes", [Router,
            ActivatedRoute,
            TopicService])
    ], TopicFormComponent);
    return TopicFormComponent;
}());
export { TopicFormComponent };
//# sourceMappingURL=topic-form.component.js.map