import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OffersComponent} from './offers.component';

const routes: Routes = [
  {
    path: '',
    component: OffersComponent,
    data: {
      breadcrumb: null
    }
  }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OffersRoutingModule { }
