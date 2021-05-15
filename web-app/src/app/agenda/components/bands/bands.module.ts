import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {BandsComponent} from './bands.component';
import {MaterialSharedModule} from '../../../shared/components/material-shared.module';
import {BandsRoutingModule} from './bands-routing.module';
import {BandService} from '../../services/band.service';
import {BandApiService} from '../../../api/services/band-api.service';
import {ErrorAlertService} from '../../../shared/services/error-alert.service';

@NgModule({
  declarations: [BandsComponent],
  imports: [
    CommonModule,
    MaterialSharedModule,
    BandsRoutingModule
  ],
  providers: [BandService, BandApiService, ErrorAlertService]
})
export class BandsModule { }
