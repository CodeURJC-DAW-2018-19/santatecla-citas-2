import { Component, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { Quote, QuoteService } from './quote.service';
import { LoginService } from '../user/login.service';
import { IPageChangeEvent, TdPagingBarComponent } from '@covalent/core';

@Component({
    selector: 'quote-list',
    templateUrl: 'quote-list.component.html'
})

export class QuoteListComponent implements OnInit {

    quotes: Quote[];
    event: IPageChangeEvent;
    firstLast: boolean = true;
    QuoteService: any;
    pageSize = 10;
    total = 14;
    pageNumber = 1;
    firstPage = 0;
    pageChanged: Boolean = false;

    @ViewChild(TdPagingBarComponent) paging: TdPagingBarComponent;

    constructor(private router: Router, private service: QuoteService,
        public loginService: LoginService) {

    }

    ngOnInit() {
        this.service.findAll(this.pageNumber - 1).subscribe(
            quotes => this.quotes = quotes,
            error => console.log(error)
        );
    }

    ngDoCheck() {
        if (this.pageChanged) {
            this.service.findAll(this.pageNumber - 1).subscribe(
                quotes => this.quotes = quotes,
                error => console.log(error)
            );
            this.pageChanged = false;
        }
    }

    /**
     * Botón para añadir una cita, redirección a quote/new 
     * envía a quote-form
     */
    newQuote() {
        this.router.navigate(['quote/new']);
    }

    change(event: IPageChangeEvent): void {
        this.event = event;
        this.pageNumber = event.page;
        this.pageChanged = true;
    }

    toggleFirstLast(): void {
        this.firstLast = !this.firstLast;
    }

}