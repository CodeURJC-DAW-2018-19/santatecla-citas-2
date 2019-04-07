import * as tslib_1 from "tslib";
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { JsonpModule, HttpModule } from '@angular/http';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatIconRegistry } from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { MatButtonModule, MatListModule, MatIconModule, MatCardModule, MatMenuModule, MatInputModule, MatButtonToggleModule, MatProgressSpinnerModule, MatSelectModule, MatSlideToggleModule, MatDialogModule, MatSnackBarModule, MatToolbarModule, MatTabsModule, MatSidenavModule, MatTooltipModule, MatRippleModule, MatRadioModule, MatGridListModule, MatDatepickerModule, MatNativeDateModule, MatSliderModule, MatAutocompleteModule, } from '@angular/material';
import { CovalentCommonModule, CovalentLayoutModule, CovalentMediaModule, CovalentExpansionPanelModule, CovalentStepsModule, CovalentLoadingModule, CovalentDialogsModule, CovalentSearchModule, CovalentPagingModule, CovalentNotificationsModule, CovalentMenuModule, CovalentDataTableModule, CovalentMessageModule, } from '@covalent/core';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { DomSanitizer } from '@angular/platform-browser';
import { BookService } from './book.service';
import { LoginService } from './user/login.service';
import { BookDetailComponent } from './book-detail.component';
import { BookListComponent } from './book-list.component';
import { BookFormComponent } from './book-form.component';
import { LoginComponent } from './user/login.component';
import { routing } from './app.routing';
import { QuoteService } from './quote/quote.service';
import { QuoteListComponent } from './quote/quote-list.component';
import { QuoteFormComponent } from './quote/quote-form.component';
import { QuoteDetailComponent } from './quote/quote-detail.component';
import { TopicService } from './topic/topic.service';
import { TopicListComponent } from './topic/topic-list.component';
import { TopicFormComponent } from './topic/topic-form.component';
import { TopicDetailComponent } from './topic/topic-detail.component';
import { BasicAuthInterceptor } from './auth/auth.interceptor';
import { ErrorInterceptor } from './auth/error.interceptor';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { QuotePopupComponent } from './topic/quote-popup.component';
var AppModule = /** @class */ (function () {
    function AppModule(matIconRegistry, domSanitizer) {
        this.matIconRegistry = matIconRegistry;
        this.domSanitizer = domSanitizer;
        matIconRegistry.addSvgIconSet(domSanitizer.bypassSecurityTrustResourceUrl('/assets/symbol-defs.svg'));
    }
    AppModule = tslib_1.__decorate([
        NgModule({
            imports: [
                BrowserModule,
                BrowserAnimationsModule,
                FormsModule,
                RouterModule.forRoot([]),
                HttpClientModule,
                JsonpModule,
                /** Material Modules */
                MatButtonModule,
                MatListModule,
                MatIconModule,
                MatCardModule,
                MatMenuModule,
                MatInputModule,
                MatSelectModule,
                MatButtonToggleModule,
                MatSlideToggleModule,
                MatProgressSpinnerModule,
                MatDialogModule,
                MatSnackBarModule,
                MatToolbarModule,
                MatTabsModule,
                MatSidenavModule,
                MatTooltipModule,
                MatRippleModule,
                MatRadioModule,
                MatGridListModule,
                MatDatepickerModule,
                MatNativeDateModule,
                MatSliderModule,
                MatAutocompleteModule,
                /** Covalent Modules */
                CovalentCommonModule,
                CovalentLayoutModule,
                CovalentMediaModule,
                CovalentExpansionPanelModule,
                CovalentStepsModule,
                CovalentDialogsModule,
                CovalentLoadingModule,
                CovalentSearchModule,
                CovalentPagingModule,
                CovalentNotificationsModule,
                CovalentMenuModule,
                CovalentDataTableModule,
                CovalentMessageModule,
                /** Additional **/
                NgxChartsModule,
                HttpModule,
                routing
            ],
            declarations: [AppComponent, QuoteDetailComponent, QuoteFormComponent, QuoteListComponent, QuotePopupComponent, BookDetailComponent, BookListComponent, BookFormComponent, LoginComponent, TopicListComponent, TopicDetailComponent, TopicFormComponent],
            bootstrap: [AppComponent],
            providers: [TopicService, BookService, LoginService, QuoteService,
                { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
                { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
                { provide: LocationStrategy, useClass: HashLocationStrategy }],
        }),
        tslib_1.__metadata("design:paramtypes", [MatIconRegistry, DomSanitizer])
    ], AppModule);
    return AppModule;
}());
export { AppModule };
//# sourceMappingURL=app.module.js.map