import { RouterModule } from '@angular/router';


import { TopicListComponent } from './topic/topic-list.component';
import {TopicDetailComponent} from './topic/topic-detail.component';
import { QuoteListComponent } from './quote/quote-list.component';
import { QuotePopupComponent } from './topic/quote-popup.component';
import { TopicFormComponent } from './topic/topic-form.component';
import { QuoteFormComponent } from './quote/quote-form.component';
import { QuoteDetailComponent } from './quote/quote-detail.component';
import { IndexComponent } from './index.component';
import { ChartComponent } from './charts/chart.component';

const appRoutes = [
  
  { path: 'chart', component: ChartComponent},
  { path: 'index', component: IndexComponent},

  { path: 'quote/new', component: QuoteFormComponent},
  { path: 'quote/:id', component: QuoteDetailComponent},
  { path: 'quote/form/:id', component: QuoteFormComponent},

  { path: 'quotesPop', component: QuotePopupComponent},

  { path: 'topic/:id', component: TopicDetailComponent },
  { path: 'topics', component: IndexComponent},
  { path: 'topics/new', component:TopicFormComponent},
  { path: 'topic/form/:id', component: TopicFormComponent},
  { path: 'topic/reference/:id', component: QuotePopupComponent},

  { path: '', redirectTo: 'index', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(appRoutes);
