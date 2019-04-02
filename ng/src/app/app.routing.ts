import { QuoteDetailComponent } from './../quote/quote-detail.component';
import { Routes, RouterModule } from '@angular/router';

import { QuoteListComponent } from './quote-list.component';
import { QuoteDetailComponent } from './quote-detail.Component';
import { QuoteFormComponent } from './quote-form.component';
import { QuoteListComponent } from './quote/quote-list.component';
import { QuoteFormComponent } from './quote/quote-form.component';
import { TopicListComponent } from './topic/topic-list.component';

const appRoutes = [
  { path: 'quotes', component: QuoteListComponent },
  { path: 'quotes/new', component: QuoteFormComponent },
  { path: 'topics', component: TopicListComponent, useAsDefault: true},
  { path: 'book/:id', component: BookDetailComponent },
  { path: 'book/edit/:id', component: BookFormComponent },
  { path: '', redirectTo: 'topics', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);
