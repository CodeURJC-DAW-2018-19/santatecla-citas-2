import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from './user.service';
var UserFormComponent = /** @class */ (function () {
    function UserFormComponent(router, activatedRoute, service) {
        var _this = this;
        this.router = router;
        this.service = service;
        var id = activatedRoute.snapshot.params['id'];
        if (id) {
            service.getuser(id).subscribe(function (user) { return _this.user = user; }, function (error) { return console.error(error); });
            this.newUser = false;
        }
        else {
            this.user = { id: 0, email: " ", passwar: "" };
            this.newUser = true;
        }
    }
    UserFormComponent.prototype.cancel = function () {
        window.history.back();
    };
    UserFormComponent.prototype.save = function () {
        this.service.addUser(this.user).subscribe(function (User) { }, function (error) { return console.error('Error creating user: ' + error); });
        window.history.back();
    };
    UserFormComponent = tslib_1.__decorate([
        Component({
            templateUrl: 'user-form.component.html'
        }),
        tslib_1.__metadata("design:paramtypes", [Router,
            ActivatedRoute,
            UserService])
    ], UserFormComponent);
    return UserFormComponent;
}());
export { UserFormComponent };
//# sourceMappingURL=user-form.component.js.map