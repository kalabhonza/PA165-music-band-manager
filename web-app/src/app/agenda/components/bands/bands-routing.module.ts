import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BandsComponent} from './bands.component';

const routes: Routes = [
  {
    path: '',
    component: BandsComponent,
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
export class BandsRoutingModule { }
