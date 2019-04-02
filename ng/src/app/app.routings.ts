import { Topic } from './topic/topic.service';
import { Quote } from './quote/quote.service';
import { RouterModule } from '@angular/router';


import { QuoteListComponent } from './quote/quote-list.component';
import { QuoteDetailComponent } from './quote/quote-detail.component';
import { QuoteFormComponent } from './quote/quote-form.component';

import { TopicListComponent } from './topic/topic-list.component';
import { TopicDetailComponent } from './topic/topic-detail.component';
import { TopicFormComponent } from './topic/topic-form.component';

const appRoutes = [
  { path: 'quotes', component: QuoteListComponent, useAsDefault: true },
  { path: 'quote/new', component: QuoteFormComponent },
  { path: 'quote/:id', component: QuoteDetailComponent },
  { path: 'quote/edit/:id', component: QuoteFormComponent },
  { path: '', redirectTo: 'quotes', pathMatch: 'full' }

  { path: 'topics', component: TopicListComponent, useAsDefault: true },
  { path: 'topic/new', component: TopicFormComponent },
  { path: 'topic/:id', component: TopicDetailComponent },
  { path: 'topic/edit/:id', component: TopicFormComponent },
  { path: '', redirectTo: 'topics', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);
