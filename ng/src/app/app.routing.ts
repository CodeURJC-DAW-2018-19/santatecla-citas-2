import { RouterModule } from '@angular/router';

import { BookListComponent } from './book-list.component';
import { BookDetailComponent } from './book-detail.component';
import { BookFormComponent } from './book-form.component';
import { TopicListComponent } from './topic/topic-list.component';
import {TopicDetailComponent} from './topic/topic-detail.component';
import { QuoteListComponent } from './quote/quote-list.component';
import { QuotePopupComponent } from './topic/quote-popup.component';
import { TopicFormComponent } from './topic/topic-form.component';
import { QuoteFormComponent } from './quote/quote-form.component';
import { QuoteDetailComponent } from './quote/quote-detail.component';

const appRoutes = [
  { path: 'books', component: BookListComponent },
  { path: 'topics', component: TopicListComponent},
  { path: 'quotes', component: QuoteListComponent},
  { path: 'quote/new', component: QuoteFormComponent},
  { path: 'quote/:id', component: QuoteDetailComponent},
  { path: 'quote/form/:id', component: QuoteFormComponent},

  {path: 'quotesPop', component: QuotePopupComponent},

  { path: 'topic/:id', component: TopicDetailComponent },
  { path: 'topic/new', component: TopicFormComponent },
  { path: 'topic/form/:id', component: TopicFormComponent},
  { path: 'topic/reference/:id', component: QuotePopupComponent},


  { path: 'book/:id', component: BookDetailComponent },
  { path: 'book/edit/:id', component: BookFormComponent },
  { path: '', redirectTo: 'quotes', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);
