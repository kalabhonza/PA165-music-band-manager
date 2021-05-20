import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {BandManageComponent} from '../band-manage/band-manage.component';
import {MusiciansComponent} from './musicians.component';

const routes: Routes = [
  {
    path: '',
    component: MusiciansComponent,
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
export class MusiciansRoutingModule { }
