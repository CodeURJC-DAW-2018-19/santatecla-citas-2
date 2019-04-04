import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BookService } from './book.service';
var BookFormComponent = /** @class */ (function () {
    function BookFormComponent(_router, activatedRoute, service) {
        var _this = this;
        this._router = _router;
        this.service = service;
        var id = activatedRoute.snapshot.params['id'];
        if (id) {
            service.getBook(id).subscribe(function (book) { return _this.book = book; }, function (error) { return console.error(error); });
            this.newBook = false;
        }
        else {
            this.book = { title: '', description: '' };
            this.newBook = true;
        }
    }
    BookFormComponent.prototype.cancel = function () {
        window.history.back();
    };
    BookFormComponent.prototype.save = function () {
        this.service.saveBook(this.book).subscribe(function (book) { }, function (error) { return console.error('Error creating new book: ' + error); });
        window.history.back();
    };
    BookFormComponent = tslib_1.__decorate([
        Component({
            template: "\n  <div *ngIf=\"book\">\n  <h2>Book \"{{book.title}}\"</h2>\n  <div *ngIf=\"book.id\">\n    <label>Id: </label>{{book.id}}\n  </div>\n  <div>\n    <label>Title: </label>\n    <input [(ngModel)]=\"book.title\" placeholder=\"title\"/>\n  </div>\n  <div>\n    <label>Abstract: </label>\n    <textarea [(ngModel)]=\"book.description\" placeholder=\"description\"></textarea>\n  </div>\n  <p>\n    <button (click)=\"cancel()\">Cancel</button>\n    <button (click)=\"save()\">Save</button>\n  </p>\n  </div>"
        }),
        tslib_1.__metadata("design:paramtypes", [Router,
            ActivatedRoute,
            BookService])
    ], BookFormComponent);
    return BookFormComponent;
}());
export { BookFormComponent };
//# sourceMappingURL=book-form.component.js.map