import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {BandManageComponent} from '../band-manage/band-manage.component';
import {CreateBandComponent} from './create-band.component';

const routes: Routes = [
  {
    path: '',
    component: CreateBandComponent,
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
export class CreateBandRoutingModule { }
