import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {BandManageComponent} from '../band-manage/band-manage.component';
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
