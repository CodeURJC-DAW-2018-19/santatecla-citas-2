import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BookService } from './book.service';
import { LoginService } from './user/login.service';
var BookListComponent = /** @class */ (function () {
    function BookListComponent(router, service, loginService) {
        this.router = router;
        this.service = service;
        this.loginService = loginService;
    }
    BookListComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.service.getBooks().subscribe(function (books) { return _this.books = books; }, function (error) { return console.log(error); });
    };
    BookListComponent.prototype.newBook = function () {
        this.router.navigate(['/book/new']);
    };
    BookListComponent = tslib_1.__decorate([
        Component({
            templateUrl: 'book-list.component.html'
        }),
        tslib_1.__metadata("design:paramtypes", [Router, BookService,
            LoginService])
    ], BookListComponent);
    return BookListComponent;
}());
export { BookListComponent };
//# sourceMappingURL=book-list.component.js.map