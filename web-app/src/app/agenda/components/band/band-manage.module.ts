import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {BandComponent} from './band.component';
import {MaterialSharedModule} from '../../../shared/components/material-shared.module';
import {BandRoutingModule} from './band-routing.module';
import {BandApiService} from '../../../api/services/band-api.service';
import {BandService} from '../../services/band.service';
import {MusicianService} from '../../services/musician.service';
import {AlertMessageService} from '../../../shared/services/message-alert.service';
import {MusicianApiService} from '../../../api/services/musician-api.service';



@NgModule({
  declarations: [BandComponent],
  imports: [
    CommonModule,
    MaterialSharedModule,
    BandRoutingModule
  ],
  providers: [BandService, BandApiService, MusicianService, AlertMessageService, MusicianApiService]
})
export class BandManageModule { }
