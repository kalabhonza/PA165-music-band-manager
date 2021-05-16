import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {BandComponent} from './band.component';
import {MaterialSharedModule} from '../../../shared/components/material-shared.module';
import {BandRoutingModule} from './band-routing.module';
import {AlbumComponent} from '../album/album.component';
import {TourComponent} from '../tour/tour.component';



@NgModule({
  declarations: [BandComponent, AlbumComponent, TourComponent],
  imports: [
    CommonModule,
    MaterialSharedModule,
    BandRoutingModule
  ]
})
export class BandModule { }
