import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MusiciansComponent} from './musicians.component';
import {MusiciansRoutingModule} from './musicians-routing.module';
import {MaterialSharedModule} from '../../../shared/components/material-shared.module';



@NgModule({
  declarations: [MusiciansComponent],
  imports: [
    CommonModule,
    MaterialSharedModule,
    MusiciansRoutingModule
  ]
})
export class MusiciansModule { }
