import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BandManageComponent} from './band-manage.component';

const routes: Routes = [
  {
    path: '',
    component: BandManageComponent,
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
