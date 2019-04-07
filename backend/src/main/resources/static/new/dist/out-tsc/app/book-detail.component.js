import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from './book.service';
import { LoginService } from './user/login.service';
var BookDetailComponent = /** @class */ (function () {
    function BookDetailComponent(router, activatedRoute, service, loginService) {
        var _this = this;
        this.router = router;
        this.service = service;
        this.loginService = loginService;
        var id = activatedRoute.snapshot.params['id'];
        service.getBook(id).subscribe(function (book) { return _this.book = book; }, function (error) { return console.error(error); });
    }
    BookDetailComponent.prototype.removeBook = function () {
        var _this = this;
        var okResponse = window.confirm('Do you want to remove this book?');
        if (okResponse) {
            this.service.removeBook(this.book).subscribe(function (_) { return _this.router.navigate(['/books']); }, function (error) { return console.error(error); });
        }
    };
    BookDetailComponent.prototype.editBook = function () {
        this.router.navigate(['/book/edit', this.book.id]);
    };
    BookDetailComponent.prototype.gotoBooks = function () {
        this.router.navigate(['/books']);
    };
    BookDetailComponent = tslib_1.__decorate([
        Component({
            template: "\n  <div *ngIf=\"book\">\n  <h2>Book \"{{book.title}}\"</h2>\n  <div>\n    <p>{{book.description}}</p>\n  </div>\n  <p>\n    <button *ngIf=\"loginService.isLogged && loginService.isAdmin\" (click)=\"removeBook()\">Remove</button>\n    <button *ngIf=\"loginService.isLogged\" (click)=\"editBook()\">Edit</button>\n    <br>\n    <button (click)=\"gotoBooks()\">All Books</button>\n  </p>\n  </div>"
        }),
        tslib_1.__metadata("design:paramtypes", [Router, ActivatedRoute, BookService,
            LoginService])
    ], BookDetailComponent);
    return BookDetailComponent;
}());
export { BookDetailComponent };
//# sourceMappingURL=book-detail.component.js.map