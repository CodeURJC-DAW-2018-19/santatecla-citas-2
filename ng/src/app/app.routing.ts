import { RouterModule } from '@angular/router';

import { BookListComponent } from './book-list.component';
import { BookDetailComponent } from './book-detail.component';
import { BookFormComponent } from './book-form.component';
import { TopicListComponent } from './topic/topic-list.component';
import {TopicDetailComponent} from './topic/topic-detail.component';
import { QuoteListComponent } from './quote/quote-list.component';
import { QuotePopupComponent } from './topic/quote-popup.component';

const appRoutes = [
  { path: 'books', component: BookListComponent },
  { path: 'topics', component: TopicListComponent},
  { path: 'quotes', component: QuoteListComponent},

  {path: 'quotesPop', component: QuotePopupComponent},

  { path: 'topic/:id', component: TopicDetailComponent },
  { path: 'book/new', component: BookFormComponent },

  { path: 'book/:id', component: BookDetailComponent },
  { path: 'book/edit/:id', component: BookFormComponent },
  { path: '', redirectTo: 'quotes', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);
