import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {OffersComponent} from './offers.component';
import {MaterialSharedModule} from '../../../shared/components/material-shared.module';
import {MusicianApiService} from '../../../api/services/musician-api.service';
import {MusicianService} from '../../services/musician.service';
import {SessionService} from '../../../shared/services/session.service';
import {OffersRoutingModule} from './offers-routing.module';
import {ErrorAlertService} from '../../../shared/services/error-alert.service';



@NgModule({
  declarations: [OffersComponent],
  imports: [
    CommonModule,
    MaterialSharedModule,
    OffersRoutingModule
  ],
  providers: [MusicianApiService, MusicianService, SessionService, ErrorAlertService]
})
export class OffersModule { }
