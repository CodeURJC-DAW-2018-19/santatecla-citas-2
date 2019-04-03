import * as tslib_1 from "tslib";
import { Component, ChangeDetectorRef } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry, MatDialog } from '@angular/material';
import { TdMediaService, tdRotateAnimation } from '@covalent/core';
var AppComponent = /** @class */ (function () {
    function AppComponent(media, dialog, _changeDetectorRef, _iconRegistry, _domSanitizer) {
        this.media = media;
        this.dialog = dialog;
        this._changeDetectorRef = _changeDetectorRef;
        this._iconRegistry = _iconRegistry;
        this._domSanitizer = _domSanitizer;
        this._iconRegistry.addSvgIconInNamespace('assets', 'covalent', this._domSanitizer.bypassSecurityTrustResourceUrl('https://raw.githubusercontent.com/Teradata/covalent-quickstart/develop/src/assets/icons/covalent.svg'));
    }
    AppComponent.prototype.ngAfterViewInit = function () {
        // broadcast to all listener observables when loading the page
        this.media.broadcast();
        this._changeDetectorRef.detectChanges();
    };
    AppComponent = tslib_1.__decorate([
        Component({
            selector: 'my-app',
            templateUrl: './app.component.html',
            styleUrls: ['./app.component.css'],
            animations: [tdRotateAnimation],
        }),
        tslib_1.__metadata("design:paramtypes", [TdMediaService,
            MatDialog,
            ChangeDetectorRef,
            MatIconRegistry,
            DomSanitizer])
    ], AppComponent);
    return AppComponent;
}());
export { AppComponent };
//# sourceMappingURL=app.component.js.map