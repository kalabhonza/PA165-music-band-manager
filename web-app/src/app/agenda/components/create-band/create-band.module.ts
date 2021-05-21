import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MaterialSharedModule} from '../../../shared/components/material-shared.module';
import {CreateBandComponent} from './create-band.component';
import {CreateBandRoutingModule} from './create-band-routing.module';
import {BandService} from '../../services/band.service';
import {ManagerService} from '../../services/manager.service';
import {SessionService} from '../../../shared/services/session.service';
import {BandApiService} from '../../../api/services/band-api.service';
import {ManagerApiService} from '../../../api/services/manager-api.service';
import {AlertMessageService} from '../../../shared/services/message-alert.service';

@NgModule({
  declarations: [CreateBandComponent],
  imports: [
    CommonModule,
    CreateBandRoutingModule,
    MaterialSharedModule
  ],
  providers: [
    BandService,
    BandApiService,
    ManagerService,
    ManagerApiService,
    AlertMessageService,
    SessionService
  ]
})
export class CreateBandModule { }
