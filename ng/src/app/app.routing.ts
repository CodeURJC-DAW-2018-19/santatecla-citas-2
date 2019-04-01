import { Routes, RouterModule } from '@angular/router';

import { BookListComponent } from './book-list.component';
import { BookDetailComponent } from './book-detail.component';
import { BookFormComponent } from './book-form.component';
import { QuoteListComponent } from './quote/quote-list.component';
import { QuoteFormComponent } from './topic/topic-form.component';

const appRoutes = [
  { path: 'quotes', component: QuoteListComponent, useAsDefault: true },
  { path: 'quotes/new', component: QuoteFormComponent },
  { path: 'book/:id', component: BookDetailComponent },
  { path: 'book/edit/:id', component: BookFormComponent },
  { path: '', redirectTo: 'books', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);
