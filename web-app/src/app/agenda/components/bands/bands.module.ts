import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {BandsComponent} from './bands.component';
import {MaterialSharedModule} from '../../../shared/components/material-shared.module';
import {BandsRoutingModule} from './bands-routing.module';

@NgModule({
  declarations: [BandsComponent],
  imports: [
    CommonModule,
    MaterialSharedModule,
    BandsRoutingModule
  ]
})
export class BandsModule { }
