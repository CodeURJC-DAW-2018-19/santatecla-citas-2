import * as tslib_1 from "tslib";
import { Component, ViewChild, TemplateRef } from '@angular/core';
import { LoginService } from './login.service';
import { MatDialog } from '@angular/material';
var LoginComponent = /** @class */ (function () {
    function LoginComponent(dialog, loginService) {
        this.dialog = dialog;
        this.loginService = loginService;
    }
    LoginComponent.prototype.logIn = function (event, user, pass) {
        var _this = this;
        event.preventDefault();
        this.loginService.logIn(user, pass).subscribe(function (u) {
            console.log(u);
            _this.dialogRef.close();
        }, function (error) { return alert('Invalid user or password'); });
    };
    LoginComponent.prototype.logOut = function () {
        this.loginService.logOut().subscribe(function (response) { }, function (error) { return console.log('Error when trying to log out: ' + error); });
    };
    LoginComponent.prototype.openLoginDialog = function () {
        this.dialogRef = this.dialog.open(this.loginDialog, {
            width: '50%',
            height: '50%',
        });
    };
    tslib_1.__decorate([
        ViewChild('loginDialog'),
        tslib_1.__metadata("design:type", TemplateRef)
    ], LoginComponent.prototype, "loginDialog", void 0);
    LoginComponent = tslib_1.__decorate([
        Component({
            selector: 'login',
            templateUrl: './login.component.html'
        }),
        tslib_1.__metadata("design:paramtypes", [MatDialog,
            LoginService])
    ], LoginComponent);
    return LoginComponent;
}());
export { LoginComponent };
//# sourceMappingURL=login.component.js.map