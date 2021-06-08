import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BandComponent} from './band.component';

const routes: Routes = [
  {
    path: '',
    component: BandComponent,
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
export class BandRoutingModule { }
